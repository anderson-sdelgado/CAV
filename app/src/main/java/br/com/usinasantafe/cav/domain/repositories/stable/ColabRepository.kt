package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.utils.EmptyResult

interface ColabRepository {
    suspend fun addAll(list: List<Colab>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<Colab>>
}