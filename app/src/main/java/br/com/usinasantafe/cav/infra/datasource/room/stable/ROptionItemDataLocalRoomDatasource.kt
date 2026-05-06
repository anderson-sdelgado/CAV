package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface ROptionItemDataLocalRoomDatasource {
    suspend fun addAll(list: List<ROptionItemDataLocalRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}