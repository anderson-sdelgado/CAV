package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetSupportTeamsList {
    suspend operator fun invoke(list: List<ItemCheckBoxScreenModel>): Result<Unit>
}

class ISetSupportTeamsList @Inject constructor(
    private val cardRepository: CardRepository
): SetSupportTeamsList {

    override suspend fun invoke(list: List<ItemCheckBoxScreenModel>): Result<Unit> =
        call(getClassAndMethod()) {
            val idList = list.filter { it.flag }.map { it.id }
            cardRepository.setIdSupportTeamsList(idList).getOrThrow()
        }

}
