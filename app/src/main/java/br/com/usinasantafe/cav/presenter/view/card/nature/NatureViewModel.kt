package br.com.usinasantafe.cav.presenter.view.card.nature

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.cav.domain.usecases.card.ListNature
import br.com.usinasantafe.cav.domain.usecases.card.SetListNature
import br.com.usinasantafe.cav.domain.usecases.update.UpdateTableNature
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
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

data class NatureState(
    val flagAccess: Boolean = false,
    override val status: UpdateStatusState = UpdateStatusState()
) : UiStateWithStatus<NatureState> {

    override fun copyWithStatus(status: UpdateStatusState): NatureState =
        copy(status = status)

}

@HiltViewModel
class NatureViewModel @Inject constructor(
    private val listNature: ListNature,
    private val updateTableNature: UpdateTableNature,
    private val setListNature: SetListNature
) : ViewModel() {

    val list = mutableStateListOf<ItemCheckBoxModel>()

    private val _uiState = MutableStateFlow(NatureState())
    val uiState = _uiState.asStateFlow()

    private val state get() = uiState.value

    private fun updateState(block: NatureState.() -> NatureState) {
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
            listNature().getOrThrow()
        }
            .onSuccess {
                list.clear()
                list.addAll(it)
            }
            .onFailureUpdate(getClassAndMethod(), ::updateState)
    }

    fun save() = viewModelScope.launch {
        runCatching {
            setListNature(list.toList()).getOrThrow()
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

    suspend fun updateAllDatabase(): Flow<NatureState> =
        executeUpdateSteps(
            steps = listOf(updateTableNature(sizeUpdate())),
            getState = { _uiState.value },
            getStatus = { it.status },
            copyStateWithStatus = { state, status -> state.copy(status = status) },
            classAndMethod = getClassAndMethod(),
        )

}