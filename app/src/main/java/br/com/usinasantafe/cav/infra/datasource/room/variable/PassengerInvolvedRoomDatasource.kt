package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerInvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface PassengerInvolvedRoomDatasource {
    suspend fun add(model: PassengerInvolvedRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerInvolvedRoomModel>>
    suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult
}