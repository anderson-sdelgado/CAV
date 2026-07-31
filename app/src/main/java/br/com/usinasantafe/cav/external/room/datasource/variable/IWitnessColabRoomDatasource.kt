package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.WitnessColabDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IWitnessColabRoomDatasource @Inject constructor(
    private val witnessColabDao: WitnessColabDao
): WitnessColabRoomDatasource {

    override suspend fun add(model: WitnessColabRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            witnessColabDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<WitnessColabRoomModel>> =
        result(getClassAndMethod()) {
            witnessColabDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            witnessColabDao.deleteByIdCard(idCard)
        }

}