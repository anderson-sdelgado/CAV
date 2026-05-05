package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class ITypeAccidentRepository @Inject constructor(

): TypeAccidentRepository {

    override suspend fun addAll(list: List<TypeAccident>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun listAll(token: String): Result<List<TypeAccident>> {
        TODO("Not yet implemented")
    }

}