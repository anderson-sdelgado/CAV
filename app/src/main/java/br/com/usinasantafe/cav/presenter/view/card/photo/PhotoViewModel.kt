package br.com.usinasantafe.cav.presenter.view.card.photo

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_PHOTOS = 4

data class PhotoState(
    val photos: List<Uri> = emptyList(),
    val newPhoto: Uri? = null,
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<PhotoState> {

    override fun copyWithStatus(status: UiStatusState): PhotoState =
        copy(status = status)

}

@HiltViewModel
class PhotoViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: PhotoState.() -> PhotoState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun addPhoto(uri: Uri) {

        if (state.photos.size >= MAX_PHOTOS)
            return

        if (state.photos.contains(uri))
            return

        updateState {
            copy(
                photos = photos + uri,
                newPhoto = null
            )
        }
    }

    fun removePhoto(uri: Uri) {
        updateState {
            copy(
                photos = photos - uri
            )
        }
    }

    fun setNewPhoto(uri: Uri?) {
        updateState {
            copy(newPhoto = uri)
        }
    }

}