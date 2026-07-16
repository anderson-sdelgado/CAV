package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel

interface WitnessRoomDatasource {
    suspend fun add(model: WitnessRoomModel): Result<Int>
}