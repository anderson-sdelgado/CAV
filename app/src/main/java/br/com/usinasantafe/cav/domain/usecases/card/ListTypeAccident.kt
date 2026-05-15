package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListTypeAccident {
    suspend operator fun invoke(): Result<List<ItemCheckBoxModel>>
}

class IListTypeAccident @Inject constructor(
    private val typeAccidentRepository: TypeAccidentRepository,
    private val cardRepository: CardRepository
): ListTypeAccident {

    override suspend fun invoke(): Result<List<ItemCheckBoxModel>> =
        call(getClassAndMethod()) {
            val entityList = typeAccidentRepository.listAll().getOrThrow()
            val idList = cardRepository.listIdTypeAccident().getOrThrow()
            val idSet = idList.toSet()
            entityList.map { entity ->
                ItemCheckBoxModel(
                    id = entity.id,
                    desc = entity.description,
                    flag = idSet.contains(entity.id)
                )
            }
        }

}