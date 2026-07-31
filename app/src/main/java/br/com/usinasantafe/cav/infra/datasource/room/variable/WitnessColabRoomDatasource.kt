package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface WitnessColabRoomDatasource {
    suspend fun add(model: WitnessColabRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<WitnessColabRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}