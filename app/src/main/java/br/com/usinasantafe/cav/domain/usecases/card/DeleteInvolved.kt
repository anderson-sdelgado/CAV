package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteInvolvedExternal {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class IDeleteInvolvedExternal @Inject constructor(
    private val cardRepository: CardRepository
): DeleteInvolvedExternal {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository){
                when (flowNote) {
                    FlowNote.PASSENGER_COLAB -> deletePassengerColab(idMain, idSecondary).getOrThrow()
                    FlowNote.INVOLVED_COLAB -> deleteInvolvedColab(idMain).getOrThrow()
                    FlowNote.WITNESS_COLAB -> deleteWitnessColab(idMain).getOrThrow()
                    FlowNote.WITNESS_EXTERNAL -> deleteWitnessExternal(idMain).getOrThrow()
                    FlowNote.INVOLVED_EXTERNAL -> deleteInvolvedExternal(idMain).getOrThrow()
                    else -> deletePassengerExternal(idMain, idSecondary).getOrThrow()
                }
            }

        }

}
