package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.VehicleExternalDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleExternalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IVehicleExternalRoomDatasource @Inject constructor(
    private val vehicleExternalDao: VehicleExternalDao
): VehicleExternalRoomDatasource {

    override suspend fun add(model: VehicleExternalRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            vehicleExternalDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<VehicleExternalRoomModel>> =
        result(getClassAndMethod()) {
            vehicleExternalDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            vehicleExternalDao.deleteByIdCard(idCard)
        }

}