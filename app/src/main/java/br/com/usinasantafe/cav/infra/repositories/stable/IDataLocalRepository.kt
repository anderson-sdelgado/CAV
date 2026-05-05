package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IDataLocalRepository @Inject constructor(

): DataLocalRepository {

    override suspend fun addAllItem(list: List<ItemDataLocal>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun addAllOption(list: List<OptionDataLocal>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun addAllROptionItem(list: List<ROptionItemDataLocal>): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllItem(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllOption(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllROptionItem(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun listAllItem(token: String): Result<List<ItemDataLocal>> {
        TODO("Not yet implemented")
    }

    override suspend fun listAllOption(token: String): Result<List<OptionDataLocal>> {
        TODO("Not yet implemented")
    }

    override suspend fun listAllROptionItem(token: String): Result<List<ROptionItemDataLocal>> {
        TODO("Not yet implemented")
    }

}