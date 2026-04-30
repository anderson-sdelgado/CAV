package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetLocal {
    suspend operator fun invoke(
        address: String,
        latitude: Long,
        longitude: Long
    ): Result<Unit>
}

class ISetLocal @Inject constructor(
): SetLocal {

    override suspend fun invoke(
        address: String,
        latitude: Long,
        longitude: Long
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}