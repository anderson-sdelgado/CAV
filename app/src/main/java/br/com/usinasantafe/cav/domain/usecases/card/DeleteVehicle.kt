package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteVehicle {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteVehicle @Inject constructor(
): DeleteVehicle {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}