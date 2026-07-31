package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface WitnessExternalRoomDatasource {
    suspend fun add(model: WitnessExternalRoomModel): Result<Int>
    suspend fun listByIdCard(idCard: Int): Result<List<WitnessExternalRoomModel>>
    suspend fun deleteByIdCard(idCard: Int): EmptyResult
}