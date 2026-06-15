package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteVehicleInvolved {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteVehicleInvolved @Inject constructor(
    private val cardRepository: CardRepository
): DeleteVehicleInvolved {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            cardRepository.deleteVehicleInvolved(id).getOrThrow()
        }

}
