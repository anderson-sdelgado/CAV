package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipRoomDatasource {
    suspend fun addAll(list: List<EquipRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun hasNro(nro: Long): Result<Boolean>
    suspend fun getIdByNro(nro: Long): Result<Int>
}