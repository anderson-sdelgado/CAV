package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetPhone {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetPhone @Inject constructor(
    private val cardRepository: CardRepository
): GetPhone {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when (flowNote) {
                    FlowNote.DRIVER -> getPhoneDriver(idMain)
                    FlowNote.INVOLVED -> getPhoneInvolved(idMain)
                    FlowNote.WITNESS -> getPhoneWitness(idMain)
                    else -> getPhonePassengerInvolved(idMain, idSecondary)
                }.getOrThrow() ?: ""
            }
        }

}
