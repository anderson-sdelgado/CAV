package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface WitnessRoomDatasource {
    suspend fun add(model: WitnessRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<WitnessRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}