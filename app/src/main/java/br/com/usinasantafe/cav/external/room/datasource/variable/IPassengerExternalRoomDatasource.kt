package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.PassengerExternalDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerExternalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IPassengerExternalRoomDatasource @Inject constructor(
    private val passengerExternalDao: PassengerExternalDao
): PassengerExternalRoomDatasource {

    override suspend fun add(model: PassengerExternalRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            passengerExternalDao.insert(model).toInt()
        }

    override suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerExternalRoomModel>> =
        result(getClassAndMethod()) {
            passengerExternalDao.listByIdVehicleList(idVehicleList)
        }

    override suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            passengerExternalDao.deleteByIdVehicleList(idVehicleList)
        }

}