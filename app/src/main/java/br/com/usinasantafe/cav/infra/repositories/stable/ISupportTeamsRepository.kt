package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class ISupportTeamsRepository @Inject constructor(

): SupportTeamsRepository {

    override suspend fun addAll(list: List<SupportTeams>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun listAll(token: String): Result<List<SupportTeams>> {
        TODO("Not yet implemented")
    }

}