package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetName {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String>
}

class IGetName @Inject constructor(
    private val cardRepository: CardRepository
): GetName {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when (flowNote) {
                    FlowNote.DRIVER -> getNameDriver(idMain)
                    FlowNote.INVOLVED -> getNameInvolved(idMain)
                    FlowNote.WITNESS -> getNameWitness(idMain)
                    else -> getNamePassengerInvolved(idMain, idSecondary)
                }.getOrThrow() ?: ""
            }
        }

}
