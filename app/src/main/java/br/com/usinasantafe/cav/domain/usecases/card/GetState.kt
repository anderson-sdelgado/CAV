package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetState {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<State>
}

class IGetState @Inject constructor(
    private val cardRepository: CardRepository
): GetState {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<State> =
        call(getClassAndMethod()) {
            when (flowNote) {
                FlowNote.COLAB -> cardRepository.getStateColab(idMain)
                FlowNote.PASSENGER_COLAB -> cardRepository.getStatePassengerColab(idMain, idSecondary)
                FlowNote.DRIVER -> cardRepository.getStateDriver(idMain)
                FlowNote.PASSENGER_INVOLVED -> cardRepository.getStatePassengerInvolved(idMain, idSecondary)
                FlowNote.INVOLVED -> cardRepository.getStateInvolved(idMain)
                else -> cardRepository.getStateWitness(idMain)
            }.getOrThrow()
        }

}
