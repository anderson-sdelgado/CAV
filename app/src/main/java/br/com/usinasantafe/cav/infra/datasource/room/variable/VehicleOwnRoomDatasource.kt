package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel

interface VehicleOwnRoomDatasource {
    suspend fun add(model: VehicleOwnRoomModel): Result<Int>
}