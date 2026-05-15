package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.collections.toSet

interface ListNature {
    suspend operator fun invoke(): Result<List<ItemCheckBoxModel>>
}

class IListNature @Inject constructor(
    private val natureRepository: NatureRepository,
    private val cardRepository: CardRepository
): ListNature {

    override suspend fun invoke(): Result<List<ItemCheckBoxModel>> =
        call(getClassAndMethod()) {
            val entityList = natureRepository.listAll().getOrThrow()
            val idList = cardRepository.listIdNature().getOrThrow()
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