package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.EquipSecDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.EquipSecRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IEquipSecRoomDatasource @Inject constructor(
    private val equipSecDao: EquipSecDao
): EquipSecRoomDatasource {

    override suspend fun add(model: EquipSecRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            equipSecDao.insert(model).toInt()
        }

}