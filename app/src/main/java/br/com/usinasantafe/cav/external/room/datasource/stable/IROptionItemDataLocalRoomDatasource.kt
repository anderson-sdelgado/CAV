package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.ROptionItemDataLocalDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.ROptionItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IROptionItemDataLocalRoomDatasource @Inject constructor(
    private val rOptionItemDataLocalDao: ROptionItemDataLocalDao
): ROptionItemDataLocalRoomDatasource {

    override suspend fun addAll(list: List<ROptionItemDataLocalRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            rOptionItemDataLocalDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            rOptionItemDataLocalDao.deleteAll()
        }

}