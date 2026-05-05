package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class INatureRepository @Inject constructor(

): NatureRepository {

    override suspend fun addAll(list: List<Nature>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun listAll(token: String): Result<List<Nature>> {
        TODO("Not yet implemented")
    }

}