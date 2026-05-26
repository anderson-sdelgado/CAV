package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetColab {
    suspend operator fun invoke(
        option: Option,
        type: Type,
        id: Int,
        regColab: String
    ): Result<Unit>
}

class ISetColab @Inject constructor(
): SetColab {

    override suspend fun invoke(
        option: Option,
        type: Type,
        id: Int,
        regColab: String
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}