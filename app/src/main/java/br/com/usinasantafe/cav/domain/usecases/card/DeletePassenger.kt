package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeletePassenger {
    suspend operator fun invoke(
        idSelection: Int,
        flowNote: FlowNote,
        idMain: Int
    ): Result<Unit>
}

class IDeletePassenger @Inject constructor(
    private val cardRepository: CardRepository
): DeletePassenger {

    override suspend fun invoke(
        idSelection: Int,
        flowNote: FlowNote,
        idMain: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository){
                when (flowNote) {
                    FlowNote.PASSENGER_COLAB -> deletePassengerColab(idMain, idSelection).getOrThrow()
                    else -> deletePassengerInvolved(idMain, idSelection).getOrThrow()
                }
            }

        }

}
