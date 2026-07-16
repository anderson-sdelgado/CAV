package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel

interface PassengerColabRoomDatasource {
    suspend fun add(model: PassengerColabRoomModel): Result<Int>
}