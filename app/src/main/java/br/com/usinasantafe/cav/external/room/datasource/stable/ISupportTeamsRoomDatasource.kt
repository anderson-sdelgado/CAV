package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.SupportTeamsRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class ISupportTeamsRoomDatasource @Inject constructor(
    private val supportTeamsDao: SupportTeamsDao
): SupportTeamsRoomDatasource {

    override suspend fun addAll(list: List<SupportTeamsRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            supportTeamsDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            supportTeamsDao.deleteAll()
        }

}