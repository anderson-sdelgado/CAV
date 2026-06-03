package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetPlate {
    suspend operator fun invoke(idMain: Int): Result<String>
}

class IGetPlate @Inject constructor(
): GetPlate {

    override suspend fun invoke(idMain: Int): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}