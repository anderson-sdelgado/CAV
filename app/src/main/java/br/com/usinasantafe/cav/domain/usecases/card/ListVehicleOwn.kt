package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListVehicleOwn {
    suspend operator fun invoke(): Result<List<VehicleScreenModel>>
}

class IListVehicleOwn @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository,
    private val equipRepository: EquipRepository
): ListVehicleOwn {

    override suspend fun invoke(): Result<List<VehicleScreenModel>> =
        call(getClassAndMethod()) {
            val entityList = cardRepository.listVehicleOwn().getOrThrow()
            entityList.map { entity ->
                val name = colabRepository.getNameByReg(entity.colabCard.reg!!).getOrThrow()
                val equipEntity = equipRepository.getById(entity.equipCard.id!!).getOrThrow()
                VehicleScreenModel(
                    id = entity.id!!,
                    vehicle = "${equipEntity.nro} - ${equipEntity.description}",
                    driver = "${entity.colabCard.reg!!} - $name"
                )
            }
        }

}
