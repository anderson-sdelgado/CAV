package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetSupportTeams {
    suspend operator fun invoke(): Result<String>
}

class IGetSupportTeams @Inject constructor(
    private val cardRepository: CardRepository,
    private val supportTeamsRepository: SupportTeamsRepository
): GetSupportTeams {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            val idList = cardRepository.listIdSupportTeams().getOrThrow()
            val entityList = supportTeamsRepository.listByIdList(idList).getOrThrow()
            if(entityList.isEmpty()) return@call "-"
            entityList.joinToString(separator = " - ") { it.description }
        }

}