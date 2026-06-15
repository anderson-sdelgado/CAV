package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListInvolved {
    suspend operator fun invoke(): Result<List<ItemListScreenModel>>
}

class IListInvolved @Inject constructor(
    private val cardRepository: CardRepository
): ListInvolved {

    override suspend fun invoke(): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val list = cardRepository.listInvolved().getOrThrow()
            list.map {
                ItemListScreenModel(
                    id = it.id!!,
                    desc = "${it.document ?: '-' } - ${it.name ?: '-' }"
                )
            }
        }

}
