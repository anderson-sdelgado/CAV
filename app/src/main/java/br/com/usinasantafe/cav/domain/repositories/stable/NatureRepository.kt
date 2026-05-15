package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.utils.EmptyResult

interface NatureRepository {
    suspend fun addAll(list: List<Nature>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<Nature>>
    suspend fun listAll(): Result<List<Nature>>
    suspend fun listByIdList(idList: List<Int>): Result<List<Nature>>
}