package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDocument {
    suspend operator fun invoke(
        cpf: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetDocument @Inject constructor(
    private val cardRepository: CardRepository
): SetDocument {

    override suspend fun invoke(
        cpf: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> setDocument(cpf)
                    flowNote == FlowNote.DRIVER -> updateDocumentDriver(cpf, idMain)
                    flowNote == FlowNote.INVOLVED_EXTERNAL -> updateDocumentInvolved(cpf, idMain)
                    else -> updateDocumentPassengerInvolved(cpf, idMain, idSecondary)
                }.getOrThrow()
            }
        }

}
