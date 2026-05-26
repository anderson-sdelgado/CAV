package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetRegColab {
    suspend operator fun invoke(
        option: Option,
        type: Type,
        id: Int
    ): Result<String>
}

class IGetRegColab @Inject constructor(
): GetRegColab {

    override suspend fun invoke(
        option: Option,
        type: Type,
        id: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}