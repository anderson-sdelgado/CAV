package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel

interface EquipSecRoomDatasource {
    suspend fun add(model: EquipSecRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<EquipSecRoomModel>>
}