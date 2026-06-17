package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListWitness {
    suspend operator fun invoke(): Result<List<ItemListScreenModel>>
}

class IListWitness @Inject constructor(
    private val cardRepository: CardRepository
): ListWitness {

    override suspend fun invoke(): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val list = cardRepository.listWitness().getOrThrow()
            list.map {
                ItemListScreenModel(
                    id = it.id!!,
                    desc = "${it.document ?: '-' } - ${it.name ?: '-' }"
                )
            }
        }

}
