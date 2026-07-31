package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface VehicleExternalRoomDatasource {
    suspend fun add(model: VehicleExternalRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<VehicleExternalRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}