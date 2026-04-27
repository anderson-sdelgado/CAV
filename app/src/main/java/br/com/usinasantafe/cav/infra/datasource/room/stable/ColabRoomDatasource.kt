package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface ColabRoomDatasource {
    suspend fun addAll(list: List<ColabRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}