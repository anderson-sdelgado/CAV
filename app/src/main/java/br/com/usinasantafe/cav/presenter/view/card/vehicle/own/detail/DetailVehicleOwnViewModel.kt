package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.GetDetailVehicleOwn
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureHandled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailVehicleOwnState(
    val option: Option = Option.INSERT,
    val typeDetail: TypeDetail = TypeDetail.EQUIP_VEHICLE,
    val text: String = "",
    val flagAccess: Boolean = false,
    val flagDialog: Boolean = false,
    val flagFailure: Boolean = false,
    val failure: String = "",
    val errors: Errors = Errors.EXCEPTION,
)

@HiltViewModel
class DetailVehicleOwnViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val getDetailVehicleOwn: GetDetailVehicleOwn,
    private val setDetailVehicleOwn: SetDetailVehicleOwn
) : ViewModel() {

    private val option: Int = saveStateHandle[OPTION_ARG]!!
    private val typeDetail: Int = saveStateHandle[TYPE_DETAIL_ARG]!!
    private val _uiState = MutableStateFlow(DetailVehicleOwnState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: DetailVehicleOwnState.() -> DetailVehicleOwnState) {
        _uiState.update(block)
    }
    
    fun setCloseDialog() = updateState { copy(flagDialog = false) }

    init {
        updateState {
            copy(
                option = Option.entries[this@DetailVehicleOwnViewModel.option],
                typeDetail = TypeDetail.entries[this@DetailVehicleOwnViewModel.typeDetail]
            )
        }
    }

    fun onTextChanged(detail: String) {
        _uiState.update {
            it.copy(text = detail)
        }
    }

    fun recoverData() = viewModelScope.launch {
        runCatching {
            getDetailVehicleOwn(state.option, state.typeDetail).getOrThrow()
        }
            .onSuccess { updateState { copy(text = it) } }
            .onFailureHandled(getClassAndMethod(), ::onError)
    }

    fun set() = viewModelScope.launch {

    }

    private fun onError(failure: String, errors: Errors = Errors.EXCEPTION) = updateState { copy(flagDialog = true, failure = failure, errors = errors) }

}