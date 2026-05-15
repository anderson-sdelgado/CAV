package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetNatureList {
    suspend operator fun invoke(list: List<ItemCheckBoxModel>): EmptyResult
}

class ISetNatureList @Inject constructor(
    private val cardRepository: CardRepository
): SetNatureList {

    override suspend fun invoke(list: List<ItemCheckBoxModel>): EmptyResult =
        call(getClassAndMethod()) {
            val idList = list.filter { it.flag }.map { it.id }
            cardRepository.setIdNatureList(idList).getOrThrow()
        }

}