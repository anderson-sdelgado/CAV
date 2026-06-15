package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListEquipSec {
    suspend operator fun invoke(id: Int): Result<List<ItemListScreenModel>>
}

class IListEquipSec @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): ListEquipSec {

    override suspend fun invoke(id: Int): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val idEquipList = cardRepository.listIdEquipSecondary(id).getOrThrow()
            val entityList = equipRepository.listByIdList(idEquipList).getOrThrow()
            entityList.map {
                ItemListScreenModel(
                    id = it.id,
                    desc = "${it.nro} - ${it.description}"
                )
            }
        }

}
