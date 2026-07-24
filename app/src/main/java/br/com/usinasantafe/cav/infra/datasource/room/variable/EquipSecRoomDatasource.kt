package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipSecRoomDatasource {
    suspend fun add(model: EquipSecRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<EquipSecRoomModel>>
    suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult
}