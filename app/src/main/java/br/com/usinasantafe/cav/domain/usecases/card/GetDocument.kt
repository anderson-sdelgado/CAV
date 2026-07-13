package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDocument {
    suspend operator fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetDocument @Inject constructor(
    private val cardRepository: CardRepository
): GetDocument {

    override suspend fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> getDocument()
                    flowNote == FlowNote.DRIVER -> getDocumentDriver(idMain)
                    flowNote == FlowNote.INVOLVED -> getDocumentInvolved(idMain)
                    else -> getDocumentPassengerInvolved(idMain, idSecondary)
                }.getOrThrow() ?: ""
            }
        }

}
