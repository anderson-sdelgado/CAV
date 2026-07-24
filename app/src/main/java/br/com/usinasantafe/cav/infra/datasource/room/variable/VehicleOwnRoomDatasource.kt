package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface VehicleOwnRoomDatasource {
    suspend fun add(model: VehicleOwnRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<VehicleOwnRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}