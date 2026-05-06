package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.NatureRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class INatureRoomDatasource @Inject constructor(
    private val natureDao: NatureDao
): NatureRoomDatasource {

    override suspend fun addAll(list: List<NatureRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            natureDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            natureDao.deleteAll()
        }

}