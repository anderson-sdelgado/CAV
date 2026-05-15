package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetTypeAccidentList {
    suspend operator fun invoke(list: List<ItemCheckBoxScreenModel>): Result<Unit>
}

class ISetTypeAccidentList @Inject constructor(
    private val cardRepository: CardRepository
): SetTypeAccidentList {

    override suspend fun invoke(list: List<ItemCheckBoxScreenModel>): Result<Unit> =
        call(getClassAndMethod()) {
            val idList = list.filter { it.flag }.map { it.id }
            cardRepository.setIdTypeAccidentList(idList).getOrThrow()
        }

}