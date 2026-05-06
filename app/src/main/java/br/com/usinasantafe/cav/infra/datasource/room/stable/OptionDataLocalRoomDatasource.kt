package br.com.usinasantafe.cav.infra.datasource.room.stable

import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface OptionDataLocalRoomDatasource {
    suspend fun addAll(list: List<OptionDataLocalRoomModel>): EmptyResult
    suspend fun deleteAll(): EmptyResult
}