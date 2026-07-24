package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.PassengerInvolvedDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerInvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IPassengerInvolvedRoomDatasource @Inject constructor(
    private val passengerInvolvedDao: PassengerInvolvedDao
): PassengerInvolvedRoomDatasource {

    override suspend fun add(model: PassengerInvolvedRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            passengerInvolvedDao.insert(model).toInt()
        }

    override suspend fun listByIdVehicleList(idVehicleList: List<Int>): Result<List<PassengerInvolvedRoomModel>> =
        result(getClassAndMethod()) {
            passengerInvolvedDao.listByIdVehicleList(idVehicleList)
        }

    override suspend fun deleteByIdVehicleList(idVehicleList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            passengerInvolvedDao.deleteByIdVehicleList(idVehicleList)
        }

}