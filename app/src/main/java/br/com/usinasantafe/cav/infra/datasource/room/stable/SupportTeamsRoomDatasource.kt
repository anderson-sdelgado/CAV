package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface SupportTeamsRoomDatasource {
    suspend fun addAll(list: List<SupportTeamsRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}