package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListInvolvedExternal {
    suspend operator fun invoke(): Result<List<ItemListScreenModel>>
}

class IListInvolvedExternal @Inject constructor(
    private val cardRepository: CardRepository
): ListInvolvedExternal {

    override suspend fun invoke(): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val list = cardRepository.listInvolvedExternal().getOrThrow()
            list.map {
                ItemListScreenModel(
                    id = it.id!!,
                    desc = "${it.phone ?: '-' } - ${it.name ?: '-' }"
                )
            }
        }

}
