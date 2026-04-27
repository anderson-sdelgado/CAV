package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipRoomDatasource {
    suspend fun addAll(list: List<EquipRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}