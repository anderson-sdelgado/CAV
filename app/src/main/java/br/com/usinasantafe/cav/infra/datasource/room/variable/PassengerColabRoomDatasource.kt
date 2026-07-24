package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface PassengerColabRoomDatasource {
    suspend fun add(model: PassengerColabRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerColabRoomModel>>
    suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult
}