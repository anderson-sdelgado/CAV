package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListInvolvedColab {
    suspend operator fun invoke(): Result<List<ItemListScreenModel>>
}

class IListInvolvedColab @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository
): ListInvolvedColab {

    override suspend fun invoke(): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val list = cardRepository.listInvolvedColab().getOrThrow()
            val regList = list.map { it.reg!! }
            val entityList = colabRepository.listColabByRegList(regList).getOrThrow()
            list.map { colabCard ->
                val fullData = entityList.find { it.reg == colabCard.reg }
                ItemListScreenModel(
                    id = colabCard.id!!,
                    desc = "${colabCard.reg} - ${fullData?.name}"
                )
            }
        }

}