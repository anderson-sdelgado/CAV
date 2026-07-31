package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface InvolvedColabRoomDatasource {
    suspend fun add(model: InvolvedColabRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<InvolvedColabRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}