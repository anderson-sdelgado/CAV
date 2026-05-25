package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDetailVehicleOwn {
    suspend operator fun invoke(
        option: Option,
        typeDetail: TypeDetail,
        detail: String
    ): Result<Unit>
}

class ISetDetailVehicleOwn @Inject constructor(
): SetDetailVehicleOwn {

    override suspend fun invoke(
        option: Option,
        typeDetail: TypeDetail,
        detail: String
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}