package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.VehicleOwnDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleOwnRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IVehicleOwnRoomDatasource @Inject constructor(
    private val vehicleOwnDao: VehicleOwnDao
): VehicleOwnRoomDatasource {
    override suspend fun add(model: VehicleOwnRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            vehicleOwnDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<VehicleOwnRoomModel>> =
        result(getClassAndMethod()) {
            vehicleOwnDao.listByIdCard(idCard)
        }

}