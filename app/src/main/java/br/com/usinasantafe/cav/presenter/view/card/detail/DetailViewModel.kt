package br.com.usinasantafe.cav.presenter.view.card.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDetailVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.SetDetailVehicleOwn
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UiStatusState
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureState
import br.com.usinasantafe.cav.utils.onSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

data class DetailState(
    val option: Option = Option.INSERT,
    val typeDetail: TypeDetail = TypeDetail.EQUIP,
    val text: String = "",
    override val status: UiStatusState = UiStatusState()
) : UiStateWithStatus<DetailState> {

    override fun copyWithStatus(status: UiStatusState): DetailState =
        copy(status = status)

}

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailVehicleOwn: GetDetailVehicleOwn,
    private val setDetailVehicleOwn: SetDetailVehicleOwn
) : ViewModel() {

    private val option: Int = savedStateHandle[OPTION_ARG]!!
    private val typeDetail: Int = savedStateHandle[TYPE_DETAIL_ARG]!!
    private val _uiState = MutableStateFlow(DetailState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DetailState.() -> DetailState) {
        _uiState.update(block)
    }

    fun onCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    init {
        updateState {
            copy(
                option = Option.entries[this@DetailViewModel.option],
                typeDetail = TypeDetail.entries[this@DetailViewModel.typeDetail]
            )
        }
    }

    fun onTextChanged(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getDetailVehicleOwn(state.option, state.typeDetail).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureState(getClassAndMethod(), ::updateState)
    }

    fun set() = viewModelScope.launch {
        runCatching {
            setDetailVehicleOwn(state.option, state.typeDetail, state.text).getOrThrow()
        }
            .onSuccessState(::updateState)
            .onFailureState(getClassAndMethod(), ::updateState)
    }

}