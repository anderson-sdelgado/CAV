package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetNatureList {
    suspend operator fun invoke(list: List<ItemCheckBoxScreenModel>): EmptyResult
}

class ISetNatureList @Inject constructor(
    private val cardRepository: CardRepository
): SetNatureList {

    override suspend fun invoke(list: List<ItemCheckBoxScreenModel>): EmptyResult =
        call(getClassAndMethod()) {
            val idList = list.filter { it.flag }.map { it.id }
            cardRepository.setIdNatureList(idList).getOrThrow()
        }

}
