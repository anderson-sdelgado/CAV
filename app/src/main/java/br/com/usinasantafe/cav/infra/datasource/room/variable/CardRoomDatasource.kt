package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRoomDatasource {
    suspend fun add(model: CardRoomModel): Result<Int>
    suspend fun update(id: Int, idServ: Int): EmptyResult
    suspend fun hasSend():  Result<Boolean>
    suspend fun getSend(): Result<CardRoomModel>
    suspend fun listDelete(): Result<List<CardRoomModel>>
    suspend fun deleteById(id: Int): EmptyResult
}