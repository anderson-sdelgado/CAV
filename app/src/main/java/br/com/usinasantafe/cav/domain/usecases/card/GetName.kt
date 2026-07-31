package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetName {
    suspend operator fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String>
}

class IGetName @Inject constructor(
    private val cardRepository: CardRepository
): GetName {

    override suspend fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> getName()
                    flowNote == FlowNote.DRIVER -> getNameDriver(idMain)
                    flowNote == FlowNote.INVOLVED_EXTERNAL -> getNameInvolved(idMain)
                    flowNote == FlowNote.WITNESS_EXTERNAL -> getNameWitness(idMain)
                    else -> getNamePassengerExternal(idMain, idSecondary)
                }.getOrThrow() ?: ""
            }
        }

}
