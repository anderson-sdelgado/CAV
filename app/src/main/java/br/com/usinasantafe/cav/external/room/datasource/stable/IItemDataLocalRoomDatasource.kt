package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.ItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IItemDataLocalRoomDatasource @Inject constructor(
    private val itemDataLocalDao: ItemDataLocalDao
): ItemDataLocalRoomDatasource {

    override suspend fun addAll(list: List<ItemDataLocalRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            itemDataLocalDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            itemDataLocalDao.deleteAll()
        }

}