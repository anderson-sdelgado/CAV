package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListTypeAccident {
    suspend operator fun invoke(): Result<List<ItemCheckBoxScreenModel>>
}

class IListTypeAccident @Inject constructor(
    private val typeAccidentRepository: TypeAccidentRepository,
    private val cardRepository: BasicCardRepository
): ListTypeAccident {

    override suspend fun invoke(): Result<List<ItemCheckBoxScreenModel>> =
        call(getClassAndMethod()) {
            val entityList = typeAccidentRepository.listAll().getOrThrow()
            val idList = cardRepository.listIdTypeAccident().getOrThrow()
            val idSet = idList.toSet()
            entityList.map { entity ->
                ItemCheckBoxScreenModel(
                    id = entity.id,
                    desc = entity.description,
                    flag = idSet.contains(entity.id)
                )
            }
        }

}
