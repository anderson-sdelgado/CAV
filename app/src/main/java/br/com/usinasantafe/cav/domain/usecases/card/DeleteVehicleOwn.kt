package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteVehicleOwn {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteVehicleOwn @Inject constructor(
    private val cardRepository: CardRepository
): DeleteVehicleOwn {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            cardRepository.deleteVehicleOwn(id).getOrThrow()
        }

}
