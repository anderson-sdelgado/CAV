package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.utils.EmptyResult

interface TypeAccidentRepository {
    suspend fun addAll(list: List<TypeAccident>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<TypeAccident>>
}