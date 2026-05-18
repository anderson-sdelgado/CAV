package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.DataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IROptionItemDataLocalRoomDatasource @Inject constructor(
    private val dataLocalDao: DataLocalDao
): DataLocalRoomDatasource {

    override suspend fun addAll(list: List<DataLocalRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            dataLocalDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            dataLocalDao.deleteAll()
        }

    override suspend fun getById(id: Int): Result<DataLocalRoomModel> =
        result(getClassAndMethod()) {
            dataLocalDao.getById(id).required("model")
        }

    override suspend fun listByIdOption(idOption: Int): Result<List<DataLocalRoomModel>> =
        result(getClassAndMethod()) {
            dataLocalDao.listByIdOption(idOption)
        }

}