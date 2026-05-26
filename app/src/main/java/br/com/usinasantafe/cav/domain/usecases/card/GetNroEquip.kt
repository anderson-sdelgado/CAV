package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetNroEquip {
    suspend operator fun invoke(
        option: Option,
        type: Type
    ): Result<String>
}

class IGetNroEquip @Inject constructor(
): GetNroEquip {

    override suspend fun invoke(
        option: Option,
        type: Type
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}