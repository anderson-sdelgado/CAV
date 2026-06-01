package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescVehicle {
    suspend operator fun invoke(
        idMain: Int
    ): Result<String>
}

class IGetDescVehicle @Inject constructor(
): GetDescVehicle {

    override suspend fun invoke(
        idMain: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}