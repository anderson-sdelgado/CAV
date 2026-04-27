package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.EquipRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IEquipRoomDatasource @Inject constructor(
    private val equipDao: EquipDao
): EquipRoomDatasource {

    override suspend fun addAll(list: List<EquipRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            equipDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            equipDao.deleteAll()
        }

}