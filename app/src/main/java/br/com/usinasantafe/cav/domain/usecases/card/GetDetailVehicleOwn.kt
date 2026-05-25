package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDetailVehicleOwn {
    suspend operator fun invoke(
        option: Option,
        typeDetail: TypeDetail,
    ): Result<String>
}

class IGetDetailVehicleOwn @Inject constructor(
): GetDetailVehicleOwn {

    override suspend fun invoke(
        option: Option,
        typeDetail: TypeDetail,
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}