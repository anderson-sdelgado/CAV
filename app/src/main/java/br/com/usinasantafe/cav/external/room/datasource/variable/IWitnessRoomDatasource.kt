package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.WitnessDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IWitnessRoomDatasource @Inject constructor(
    private val witnessDao: WitnessDao
): WitnessRoomDatasource {
    override suspend fun add(model: WitnessRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            witnessDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<WitnessRoomModel>> =
        result(getClassAndMethod()) {
            witnessDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            witnessDao.deleteByIdCard(idCard)
        }

}