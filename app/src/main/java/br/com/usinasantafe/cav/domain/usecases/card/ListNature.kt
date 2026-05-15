package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.collections.toSet

interface ListNature {
    suspend operator fun invoke(): Result<List<ItemCheckBoxScreenModel>>
}

class IListNature @Inject constructor(
    private val natureRepository: NatureRepository,
    private val cardRepository: CardRepository
): ListNature {

    override suspend fun invoke(): Result<List<ItemCheckBoxScreenModel>> =
        call(getClassAndMethod()) {
            val entityList = natureRepository.listAll().getOrThrow()
            val idList = cardRepository.listIdNature().getOrThrow()
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