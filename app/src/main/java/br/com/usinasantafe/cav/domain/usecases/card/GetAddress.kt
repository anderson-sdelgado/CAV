package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetAddress {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetAddress @Inject constructor(
    private val cardRepository: CardRepository
): GetAddress {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when(flowNote){
                    FlowNote.DRIVER -> getAddressDriver(idMain)
                    FlowNote.PASSENGER_INVOLVED -> getAddressPassengerInvolved(idMain, idSecondary)
                    else -> getAddressInvolved(idMain)
                }.getOrThrow() ?: ""
            }
        }

}
