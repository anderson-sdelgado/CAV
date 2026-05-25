package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetEquip {
    suspend operator fun invoke(
        option: Option,
        type: Type
    ): Result<String>
}

class IGetEquip @Inject constructor(
): GetEquip {

    override suspend fun invoke(
        option: Option,
        type: Type
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}