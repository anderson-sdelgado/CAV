package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListVehicleInvolved {
    suspend operator fun invoke(): Result<List<VehicleScreenModel>>
}

class IListVehicleInvolved @Inject constructor(
): ListVehicleInvolved {

    override suspend fun invoke(): Result<List<VehicleScreenModel>> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
