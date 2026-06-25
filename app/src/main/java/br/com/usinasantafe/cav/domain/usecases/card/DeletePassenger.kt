package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeletePassenger {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class IDeletePassenger @Inject constructor(
    private val cardRepository: CardRepository
): DeletePassenger {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository){
                when (flowNote) {
                    FlowNote.PASSENGER_COLAB -> deletePassengerColab(idMain, idSecondary).getOrThrow()
                    else -> deletePassengerInvolved(idMain, idSecondary).getOrThrow()
                }
            }

        }

}
