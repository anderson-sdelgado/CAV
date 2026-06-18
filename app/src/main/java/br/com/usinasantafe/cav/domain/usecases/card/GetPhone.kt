package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetPhone {
    suspend operator fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetPhone @Inject constructor(
    private val cardRepository: CardRepository
): GetPhone {

    override suspend fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> getPhoneInvolved()
                    flowNote == FlowNote.DRIVER -> getPhoneDriver(idMain)
                    flowNote == FlowNote.INVOLVED -> getPhoneInvolved(idMain)
                    flowNote == FlowNote.WITNESS -> getPhoneWitness(idMain)
                    else -> getPhonePassengerInvolved(idMain, idSecondary)
                }.getOrThrow() ?: ""
            }
        }

}
