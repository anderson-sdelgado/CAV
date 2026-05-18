package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface DataLocalRoomDatasource {
    suspend fun addAll(list: List<DataLocalRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun getById(id: Int): Result<DataLocalRoomModel>
    suspend fun listByIdOption(idOption: Int): Result<List<DataLocalRoomModel>>
}