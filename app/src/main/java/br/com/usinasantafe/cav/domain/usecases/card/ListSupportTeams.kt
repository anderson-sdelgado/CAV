package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListSupportTeams {
    suspend operator fun invoke(): Result<List<ItemCheckBoxScreenModel>>
}

class IListSupportTeams @Inject constructor(
    private val supportTeamsRepository: SupportTeamsRepository,
    private val cardRepository: CardRepository
): ListSupportTeams {

    override suspend fun invoke(): Result<List<ItemCheckBoxScreenModel>> =
        call(getClassAndMethod()) {
            val entityList = supportTeamsRepository.listAll().getOrThrow()
            val idList = cardRepository.listIdSupportTeams().getOrThrow()
            val idSet = idList.toSet()
            entityList.map { entity ->
                ItemCheckBoxScreenModel(
                    id = entity.id,
                    desc = entity.description,
                    flag = idSet.contains(entity.id)
                )
            }
        }

}