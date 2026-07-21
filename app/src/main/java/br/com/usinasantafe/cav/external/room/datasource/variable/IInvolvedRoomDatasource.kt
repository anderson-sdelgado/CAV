package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.InvolvedDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IInvolvedRoomDatasource @Inject constructor(
    private val involvedDao: InvolvedDao
): InvolvedRoomDatasource {

    override suspend fun add(model: InvolvedRoomModel): Result<Int> =
        result(getClassAndMethod()) {
            involvedDao.insert(model).toInt()
        }

    override suspend fun listByIdCard(idCard: Int): Result<List<InvolvedRoomModel>> =
        result(getClassAndMethod()) {
            involvedDao.listByIdCard(idCard)
        }

}