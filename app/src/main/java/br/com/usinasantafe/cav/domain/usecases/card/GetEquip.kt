package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetEquip {
    suspend operator fun invoke(id: Int): Result<String>
}

class IGetEquip @Inject constructor(
): GetEquip {

    override suspend fun invoke(id: Int): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}