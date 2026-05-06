package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.OptionDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IOptionDataLocalRoomDatasource @Inject constructor(
    private val optionDataLocalDao: OptionDataLocalDao
): OptionDataLocalRoomDatasource {

    override suspend fun addAll(list: List<OptionDataLocalRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            optionDataLocalDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            optionDataLocalDao.deleteAll()
        }

}