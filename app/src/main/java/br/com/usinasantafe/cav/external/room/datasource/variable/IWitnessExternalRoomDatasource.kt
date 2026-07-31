package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.WitnessExternalDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessExternalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IWitnessExternalRoomDatasource @Inject constructor(
    private val witnessExternalDao: WitnessExternalDao
): WitnessExternalRoomDatasource {

    override suspend fun add(model: WitnessExternalRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            witnessExternalDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<WitnessExternalRoomModel>> =
        result(getClassAndMethod()) {
            witnessExternalDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            witnessExternalDao.deleteByIdCard(idCard)
        }

}