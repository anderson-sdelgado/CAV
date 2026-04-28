package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.ColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IColabRoomDatasource @Inject constructor(
    private val colabDao: ColabDao
): ColabRoomDatasource {

    override suspend fun addAll(list: List<ColabRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            colabDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            colabDao.deleteAll()
        }

    override suspend fun hasReg(reg: Long): Result<Boolean> =
        result(getClassAndMethod()) {
            colabDao.hasReg(reg)
        }

}