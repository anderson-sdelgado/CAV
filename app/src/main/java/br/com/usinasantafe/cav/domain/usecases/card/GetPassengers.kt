package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetPassengers {
    suspend operator fun invoke(id: Int): Result<String>
}

class IGetPassengers @Inject constructor(
): GetPassengers {

    override suspend fun invoke(id: Int): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}