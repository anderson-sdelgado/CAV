package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.InvolvedColabDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedColabRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IInvolvedColabRoomDatasource @Inject constructor(
    private val involvedColabDao: InvolvedColabDao
): InvolvedColabRoomDatasource {

    override suspend fun add(model: InvolvedColabRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            involvedColabDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<InvolvedColabRoomModel>> =
        result(getClassAndMethod()) {
            involvedColabDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            involvedColabDao.deleteByIdCard(idCard)
        }
}