package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetState {
    suspend operator fun invoke(
        state: State,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class ISetState @Inject constructor(
    private val cardRepository: CardRepository
): SetState {

    override suspend fun invoke(
        state: State,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when (option) {
                    Option.INSERT -> when(flowNote) {
                        FlowNote.COLAB -> setStateColab(state)
                        FlowNote.PASSENGER_COLAB -> setStatePassengerColab(state, idMain)
                        FlowNote.DRIVER -> setStateDriver(state)
                        FlowNote.PASSENGER_INVOLVED -> setStatePassengerInvolved(state, idMain)
                        FlowNote.INVOLVED -> setStateInvolved(state)
                        else -> setStateStateWitness(state)
                    }
                    Option.EDIT -> when(flowNote) {
                        FlowNote.COLAB -> updateStateColab(state, idMain)
                        FlowNote.PASSENGER_COLAB -> updateStatePassengerColab(state, idMain, idSecondary)
                        FlowNote.DRIVER -> updateStateDriver(state, idMain)
                        FlowNote.PASSENGER_INVOLVED -> updateStatePassengerInvolved(state, idMain, idSecondary)
                        FlowNote.INVOLVED -> updateStateInvolved(state, idMain)
                        else -> updateStateWitness(state, idMain)
                    }
                }.getOrThrow()
            }
        }

}
