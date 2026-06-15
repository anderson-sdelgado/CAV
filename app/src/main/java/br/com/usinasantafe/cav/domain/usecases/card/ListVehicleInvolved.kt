package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListVehicleInvolved {
    suspend operator fun invoke(): Result<List<VehicleScreenModel>>
}

class IListVehicleInvolved @Inject constructor(
    private val cardRepository: CardRepository
): ListVehicleInvolved {

    override suspend fun invoke(): Result<List<VehicleScreenModel>> =
        call(getClassAndMethod()) {
            val entityList = cardRepository.listVehicleInvolved().getOrThrow()
            entityList.map { entity ->
                VehicleScreenModel(
                    id = entity.id!!,
                    vehicle = "${entity.vehicle.plate} - ${entity.vehicle.brand}",
                    driver = "${entity.driver.document ?: '-'} - ${entity.driver.name ?: '-'}"
                )
            }
        }

}
