package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetName {
    suspend operator fun invoke(
        name: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetName @Inject constructor(
    private val cardRepository: CardRepository
): SetName {

    override suspend fun invoke(
        name: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> setName(name)
                    flowNote == FlowNote.DRIVER -> updateNameDriver(name, idMain)
                    flowNote == FlowNote.INVOLVED -> updateNameInvolved(name, idMain)
                    flowNote == FlowNote.WITNESS -> updateNameWitness(name, idMain)
                    else -> updateNamePassengerInvolved(name, idMain, idSecondary)
                }.getOrThrow()
            }
        }

}
