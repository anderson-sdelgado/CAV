package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface PassengerExternalRoomDatasource {
    suspend fun add(model: PassengerExternalRoomModel): Result<Int>
    suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerExternalRoomModel>>
    suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult
}