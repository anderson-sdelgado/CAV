package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.utils.EmptyResult

interface SupportTeamsRepository {
    suspend fun addAll(list: List<SupportTeams>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<SupportTeams>>
    suspend fun listAll(): Result<List<SupportTeams>>
    suspend fun listByIdList(idList: List<Int>): Result<List<SupportTeams>>
}