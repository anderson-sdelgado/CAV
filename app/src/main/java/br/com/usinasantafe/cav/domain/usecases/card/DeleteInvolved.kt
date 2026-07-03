package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteInvolved {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class IDeleteInvolved @Inject constructor(
    private val cardRepository: CardRepository
): DeleteInvolved {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository){
                when (flowNote) {
                    FlowNote.WITNESS -> cardRepository.deleteWitness(idMain).getOrThrow()
                    FlowNote.INVOLVED -> cardRepository.deleteInvolved(idMain).getOrThrow()
                    FlowNote.PASSENGER_COLAB -> deletePassengerColab(idMain, idSecondary).getOrThrow()
                    else -> deletePassengerInvolved(idMain, idSecondary).getOrThrow()
                }
            }

        }

}
