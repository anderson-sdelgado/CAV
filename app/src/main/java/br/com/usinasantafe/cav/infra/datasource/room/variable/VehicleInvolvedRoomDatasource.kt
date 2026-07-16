package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel

interface VehicleInvolvedRoomDatasource {
    suspend fun add(model: VehicleInvolvedRoomModel): Result<Int>
}