package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface TypeAccidentRoomDatasource {
    suspend fun addAll(list: List<TypeAccidentRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}