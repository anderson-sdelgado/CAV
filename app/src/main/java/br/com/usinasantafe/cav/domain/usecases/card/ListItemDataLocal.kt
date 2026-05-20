package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListItemDataLocal {
    suspend operator fun invoke(id: Int): Result<List<ItemCheckBoxScreenModel>>
}

class IListItemDataLocal @Inject constructor(
    private val dataLocalRepository: DataLocalRepository,
    private val cardRepository: CardRepository,
): ListItemDataLocal {

    override suspend fun invoke(id: Int): Result<List<ItemCheckBoxScreenModel>> =
        call(getClassAndMethod()) {
            val rOptionItemDataLocalList = dataLocalRepository.listDataLocalByIdOption(id).getOrThrow()

            val idItemList = rOptionItemDataLocalList.map { it.idItem }
            val itemList = dataLocalRepository.listItemByIdList(idItemList).getOrThrow()

            val idList = cardRepository.listIdDataLocal().getOrThrow()
            val idSet = idList.toSet()

            rOptionItemDataLocalList.map { entity ->
                ItemCheckBoxScreenModel(
                    id = entity.id,
                    desc = itemList.first { it.id == entity.idItem }.description,
                    flag = idSet.contains(entity.id)
                )
            }

        }

}