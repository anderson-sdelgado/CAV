package br.com.usinasantafe.cav.presenter.view.card.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.DeletePhoto
import br.com.usinasantafe.cav.domain.usecases.card.ListPhoto
import br.com.usinasantafe.cav.domain.usecases.card.SaveCard
import br.com.usinasantafe.cav.domain.usecases.card.SetPhoto
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
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
        save(path)
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

    private fun save(path: String) = viewModelScope.launch {
        runCatching {
            setPhoto(path).getOrThrow()
        }
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
}