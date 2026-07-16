package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.PassengerColabDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IPassengerColabRoomDatasource @Inject constructor(
    private val passengerColabDao: PassengerColabDao
): PassengerColabRoomDatasource {

    override suspend fun add(model: PassengerColabRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            passengerColabDao.insert(model).toInt()
        }

}