package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface ItemDataLocalRoomDatasource {
    suspend fun addAll(list: List<ItemDataLocalRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}