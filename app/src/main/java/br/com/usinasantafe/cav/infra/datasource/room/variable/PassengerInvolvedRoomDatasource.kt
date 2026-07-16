package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerInvolvedRoomModel

interface PassengerInvolvedRoomDatasource {
    suspend fun add(model: PassengerInvolvedRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerInvolvedRoomModel>>
}