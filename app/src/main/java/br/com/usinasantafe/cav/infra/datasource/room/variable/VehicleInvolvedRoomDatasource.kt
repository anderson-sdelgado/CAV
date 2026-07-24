package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface VehicleInvolvedRoomDatasource {
    suspend fun add(model: VehicleInvolvedRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<VehicleInvolvedRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}