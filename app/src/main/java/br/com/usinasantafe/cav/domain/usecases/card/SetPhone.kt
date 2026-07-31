package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetPhone {
    suspend operator fun invoke(
        phone: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetPhone @Inject constructor(
    private val cardRepository: CardRepository
): SetPhone {

    override suspend fun invoke(
        phone: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when {
                    option == Option.INSERT -> setPhone(phone)
                    flowNote == FlowNote.DRIVER -> updatePhoneDriver(phone, idMain)
                    flowNote == FlowNote.INVOLVED_EXTERNAL -> updatePhoneInvolved(phone, idMain)
                    flowNote == FlowNote.WITNESS_EXTERNAL -> updatePhoneWitness(phone, idMain)
                    else -> updatePhonePassengerInvolved(phone, idMain, idSecondary)
                }.getOrThrow()
            }
        }

}
