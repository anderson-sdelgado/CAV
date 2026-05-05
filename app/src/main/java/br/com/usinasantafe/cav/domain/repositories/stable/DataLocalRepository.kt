package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.utils.EmptyResult

interface DataLocalRepository {
    suspend fun addAllItem(list: List<ItemDataLocal>): EmptyResult
    suspend fun addAllOption(list: List<OptionDataLocal>): EmptyResult
    suspend fun addAllROptionItem(list: List<ROptionItemDataLocal>): EmptyResult
    suspend fun deleteAllItem(): EmptyResult
    suspend fun deleteAllOption(): EmptyResult
    suspend fun deleteAllROptionItem(): EmptyResult
    suspend fun listAllItem(token: String): Result<List<ItemDataLocal>>
    suspend fun listAllOption(token: String): Result<List<OptionDataLocal>>
    suspend fun listAllROptionItem(token: String): Result<List<ROptionItemDataLocal>>
}