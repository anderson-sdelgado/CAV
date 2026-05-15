package br.com.usinasantafe.cav.presenter.view.card.typeAccident

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListTypeAccident
import br.com.usinasantafe.cav.domain.usecases.card.SetTypeAccidentList
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableTypeAccident
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.presenter.view.card.nature.NatureState
import br.com.usinasantafe.cav.utils.UiStateWithStatus
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.executeUpdateSteps
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.onFailureUpdate
import br.com.usinasantafe.cav.utils.sizeUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TypeAccidentState(
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<TypeAccidentState> {

    override fun copyWithStatus(status: UpdateStatusState): TypeAccidentState =
        copy(status = status)

}

@HiltViewModel
class TypeAccidentViewModel @Inject constructor(
    private val listTypeAccident: ListTypeAccident,
    private val updateTableTypeAccident: UpdateTableTypeAccident,
    private val setTypeAccidentList: SetTypeAccidentList
) : ViewModel() {

    val list = mutableStateListOf<ItemCheckBoxModel>()

    private val _uiState = MutableStateFlow(TypeAccidentState())
    val uiState = _uiState.asStateFlow()

    private fun updateState(block: TypeAccidentState.() -> TypeAccidentState) {
        _uiState.update(block)
    }

    fun setCloseDialog() = updateState { copy(status = status.copy(flagDialog = false, flagFailure = false)) }

    fun onCheckChange(id: Int, checked: Boolean) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list[index] = list[index].copy(flag = checked)
        }
    }

    fun list() = viewModelScope.launch {
        runCatching {
            listTypeAccident().getOrThrow()
        }
            .onSuccess {
                list.clear()
                list.addAll(it)
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun save() = viewModelScope.launch {
        runCatching {
            setTypeAccidentList(list.toList()).getOrThrow()
        }
            .onSuccess { updateState { copy(flagAccess = true) } }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun updateDatabase() = viewModelScope.launch {
        viewModelScope.launch {
            updateAllDatabase().collect { stateUpdate ->
                _uiState.value = stateUpdate
            }
        }
    }

    suspend fun updateAllDatabase(): Flow<TypeAccidentState> =
        executeUpdateSteps(
            steps = listOf(updateTableTypeAccident(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}