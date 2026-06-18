package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetState {
    suspend operator fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<State>
}

class IGetState @Inject constructor(
    private val cardRepository: CardRepository
): GetState {

    override suspend fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<State> =
        call(getClassAndMethod()) {
            when (option) {
                Option.INSERT -> {
                    when(flowNote) {
                        FlowNote.COLAB,
                        FlowNote.PASSENGER_COLAB -> cardRepository.getStateColab()
                        else -> cardRepository.getStateInvolved()
                    }
                }
                Option.EDIT -> {
                    when(flowNote) {
                        FlowNote.COLAB -> cardRepository.getStateColab(idMain)
                        FlowNote.PASSENGER_COLAB -> cardRepository.getStatePassengerColab(idMain, idSecondary)
                        FlowNote.DRIVER -> cardRepository.getStateDriver(idMain)
                        FlowNote.PASSENGER_INVOLVED -> cardRepository.getStatePassengerInvolved(idMain, idSecondary)
                        FlowNote.INVOLVED -> cardRepository.getStateInvolved(idMain)
                        else -> cardRepository.getStateWitness(idMain)
                    }
                }
            }.getOrThrow() ?: State.UNHARMED
        }

}
