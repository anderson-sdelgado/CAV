package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.VehicleInvolvedDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IVehicleInvolvedRoomDatasource @Inject constructor(
    private val vehicleInvolvedDao: VehicleInvolvedDao
): VehicleInvolvedRoomDatasource {

    override suspend fun add(model: VehicleInvolvedRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            vehicleInvolvedDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<VehicleInvolvedRoomModel>> =
        result(getClassAndMethod()) {
            vehicleInvolvedDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            vehicleInvolvedDao.deleteByIdCard(idCard)
        }

}