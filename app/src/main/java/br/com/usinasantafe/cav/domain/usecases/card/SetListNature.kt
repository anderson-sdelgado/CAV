package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetListNature {
    suspend operator fun invoke(list: List<ItemCheckBoxModel>): EmptyResult
}

class ISetListNature @Inject constructor(
    private val cardRepository: CardRepository
): SetListNature {

    override suspend fun invoke(list: List<ItemCheckBoxModel>): EmptyResult =
        call(getClassAndMethod()) {
            val idNatureList = list.filter { it.flag }.map { it.id }
            cardRepository.setIdNatureList(idNatureList).getOrThrow()
        }

}