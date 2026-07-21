package br.com.usinasantafe.cav.presenter.view.card.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeletePhoto
import br.com.usinasantafe.cav.domain.usecases.card.HasDataLocal
import br.com.usinasantafe.cav.domain.usecases.card.HasLocal
import br.com.usinasantafe.cav.domain.usecases.card.HasNature
import br.com.usinasantafe.cav.domain.usecases.card.HasTypeAccident
import br.com.usinasantafe.cav.domain.usecases.card.HasVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.ListPhoto
import br.com.usinasantafe.cav.domain.usecases.card.SaveCard
import br.com.usinasantafe.cav.domain.usecases.card.SetPhoto
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessStateAccess
import br.com.usinasantafe.cav.utils.withFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_PHOTOS = 4

data class PhotoState(
    val photos: List<String> = emptyList(),
    val newPhoto: String? = null,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PhotoState> {

    override fun copyWithStatus(status: UiStatusState): PhotoState =
        copy(status = status)

}

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val listPhoto: ListPhoto,
    private val setPhoto: SetPhoto,
    private val deletePhoto: DeletePhoto,
    private val hasLocal: HasLocal,
    private val hasNature: HasNature,
    private val hasTypeAccident: HasTypeAccident,
    private val hasDataLocal: HasDataLocal,
    private val hasVehicleOwn: HasVehicleOwn,
    private val saveCard: SaveCard
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PhotoState.() -> PhotoState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            listPhoto().getOrThrow()
        }
            .onSuccess { updateState { copy(photos = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun addPhoto(path: String) {
        if (state.photos.size >= MAX_PHOTOS) return
        if (state.photos.contains(path)) return
        updateState {
            copy(
                photos = photos + path,
                newPhoto = null
            )
        }
        set(path)
    }

    fun removePhoto(path: String) {
        updateState {
            copy(
                photos = photos - path
            )
        }
        delete(path)
    }

    fun setNewPhoto(
        path: String
    ) {
        updateState {
            copy(
                newPhoto = path
            )
        }
    }


    private fun set(path: String) = viewModelScope.launch {
        runCatching {
            setPhoto(path).getOrThrow()
        }
            .onFailureState(getClassAndMethod(), ::updateState)
    }


    fun save() = viewModelScope.launch {
        runCatching {
            if(!validate()) return@launch
            saveCard().getOrThrow()
        }
            .onSuccessStateAccess(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    private fun delete(path: String) = viewModelScope.launch {
        runCatching {
            deletePhoto(path).getOrThrow()
            val file = java.io.File(path)
            if (file.exists()) {
                file.delete()
            }
        }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    private suspend fun validate(): Boolean {
        if (!hasLocal().getOrThrow()) {
            failure(Errors.NON_EXISTENT_LOCAL)
            return false
        }
        if (!hasNature().getOrThrow()) {
            failure(Errors.NON_EXISTENT_NATURE)
            return false
        }
        if (!hasTypeAccident().getOrThrow()) {
            failure(Errors.NON_EXISTENT_TYPE_ACCIDENT)
            return false
        }
        if (!hasDataLocal().getOrThrow()) {
            failure(Errors.NON_EXISTENT_DATA_LOCAL)
            return false
        }
        if (!hasVehicleOwn().getOrThrow()) {
            failure(Errors.NON_EXISTENT_VEHICLE_OWN)
            return false
        }
        return true
    }

    private fun failure(errors: Errors) {
        updateState {
            withFailure(getClassAndMethod(), errors)
        }
    }
}