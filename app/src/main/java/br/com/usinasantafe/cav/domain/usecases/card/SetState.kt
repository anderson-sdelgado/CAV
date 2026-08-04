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
                        FlowNote.COLAB,
                        FlowNote.PASSENGER_COLAB,
                        FlowNote.INVOLVED_COLAB,
                        FlowNote.WITNESS_COLAB -> setStateColab(state)
                        else -> setStateExternal(state)
                    }
                    Option.EDIT -> when(flowNote) {
                        FlowNote.COLAB -> updateStateColab(state, idMain)
                        FlowNote.PASSENGER_COLAB -> updateStatePassengerColab(state, idMain, idSecondary)
                        FlowNote.INVOLVED_COLAB -> updateStateInvolvedColab(state, idMain)
                        FlowNote.WITNESS_COLAB -> updateStateWitnessColab(state, idMain)
                        FlowNote.DRIVER -> updateStateDriver(state, idMain)
                        FlowNote.PASSENGER_EXTERNAL -> updateStatePassengerExternal(state, idMain, idSecondary)
                        FlowNote.INVOLVED_EXTERNAL -> updateStateInvolvedExternal(state, idMain)
                        else -> updateStateWitnessExternal(state, idMain)
                    }
                }.getOrThrow()
            }
        }

}
