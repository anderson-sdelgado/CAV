package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface InvolvedRoomDatasource {
    suspend fun add(model: InvolvedRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<InvolvedRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}