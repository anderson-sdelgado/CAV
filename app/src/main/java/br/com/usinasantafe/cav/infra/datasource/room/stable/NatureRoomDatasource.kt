package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface NatureRoomDatasource {
    suspend fun addAll(list: List<NatureRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}