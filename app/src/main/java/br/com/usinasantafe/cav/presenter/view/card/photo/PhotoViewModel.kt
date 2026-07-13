package br.com.usinasantafe.cav.presenter.view.card.photo

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

data class PhotoState(
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



}