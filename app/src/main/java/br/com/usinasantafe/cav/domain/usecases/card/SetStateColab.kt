package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetStateColab {
    suspend operator fun invoke(
        option: Option,
        type: Type,
        id: Int
    ): Result<Unit>
}

class ISetStateColab @Inject constructor(
): SetStateColab {

    override suspend fun invoke(
        option: Option,
        type: Type,
        id: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}