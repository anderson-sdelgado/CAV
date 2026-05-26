package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetStateColab {
    suspend operator fun invoke(
        option: Option,
        type: Type
    ): Result<Int>
}

class IGetStateColab @Inject constructor(
): GetStateColab {

    override suspend fun invoke(
        option: Option,
        type: Type
    ): Result<Int> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}