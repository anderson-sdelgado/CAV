package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.DataLocal
import br.com.usinasantafe.cav.utils.EmptyResult

interface DataLocalRepository {
    suspend fun addAllItem(list: List<ItemDataLocal>): EmptyResult
    suspend fun addAllOption(list: List<OptionDataLocal>): EmptyResult
    suspend fun addAllDataLocal(list: List<DataLocal>): EmptyResult
    suspend fun deleteAllItem(): EmptyResult
    suspend fun deleteAllOption(): EmptyResult
    suspend fun deleteAllDataLocal(): EmptyResult
    suspend fun listAllItem(token: String): Result<List<ItemDataLocal>>
    suspend fun listAllOption(token: String): Result<List<OptionDataLocal>>
    suspend fun listAllDataLocal(token: String): Result<List<DataLocal>>
    suspend fun getROptionItemById(id: Int): Result<DataLocal>
    suspend fun getDescItemById(id: Int): Result<String>
    suspend fun getDescOptionById(id: Int): Result<String>
    suspend fun listAllOption(): Result<List<OptionDataLocal>>
    suspend fun listROptionItemByIdOption(id: Int): Result<List<DataLocal>>
    suspend fun listItemByIdList(idList: List<Int>): Result<List<ItemDataLocal>>
}