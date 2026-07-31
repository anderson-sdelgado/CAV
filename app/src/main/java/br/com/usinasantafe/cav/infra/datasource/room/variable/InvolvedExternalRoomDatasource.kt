package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface InvolvedExternalRoomDatasource {
    suspend fun add(model: InvolvedExternalRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<InvolvedExternalRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}