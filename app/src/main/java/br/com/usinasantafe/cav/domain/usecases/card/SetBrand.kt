package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetBrand {
    suspend operator fun invoke(
        text: String,
        idMain: Int
    ): Result<Unit>
}

class ISetBrand @Inject constructor(
): SetBrand {

    override suspend fun invoke(
        text: String,
        idMain: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
