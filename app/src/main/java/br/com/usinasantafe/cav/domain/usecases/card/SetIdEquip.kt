package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetIdEquip {
    suspend operator fun invoke(
        option: Option,
        type: Type,
        nroEquip: String
    ): Result<Unit>
}

class ISetIdEquip @Inject constructor(
): SetIdEquip {

    override suspend fun invoke(
        option: Option,
        type: Type,
        nroEquip: String
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}