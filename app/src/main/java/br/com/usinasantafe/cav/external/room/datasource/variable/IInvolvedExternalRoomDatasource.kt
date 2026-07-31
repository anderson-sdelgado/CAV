package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.InvolvedExternalDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedExternalRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IInvolvedExternalRoomDatasource @Inject constructor(
    private val involvedExternalDao: InvolvedExternalDao
): InvolvedExternalRoomDatasource {

    override suspend fun add(model: InvolvedExternalRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            involvedExternalDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<InvolvedExternalRoomModel>> =
        result(getClassAndMethod()) {
            involvedExternalDao.listByIdCard(idCard)
        }

    override suspend fun deleteByIdCard(idCard: Int): EmptyResult =
        result(getClassAndMethod()) {
            involvedExternalDao.deleteByIdCard(idCard)
        }

}