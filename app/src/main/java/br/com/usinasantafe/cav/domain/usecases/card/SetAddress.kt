package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetAddress {
    suspend operator fun invoke(
        address: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetAddress @Inject constructor(
    private val cardRepository: CardRepository
): SetAddress {

    override suspend fun invoke(
        address: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when(flowNote) {
                    FlowNote.DRIVER -> updateAddressDriver(address, idMain)
                    FlowNote.INVOLVED -> updateAddressInvolved(address, idMain)
                    else -> updateAddressPassengerInvolved(address, idMain, idSecondary)
                }.getOrThrow()
            }
        }

}
