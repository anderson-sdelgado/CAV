package br.com.usinasantafe.cav.infra.datasource.room.variable

import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel

interface CardRoomDatasource {
    suspend fun add(model: CardRoomModel): Result<Int>
}