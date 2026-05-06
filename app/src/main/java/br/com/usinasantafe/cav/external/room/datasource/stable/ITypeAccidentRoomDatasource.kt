package br.com.usinasantafe.cav.external.room.datasource.stable

import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.infra.datasource.room.stable.TypeAccidentRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class ITypeAccidentRoomDatasource @Inject constructor(
    private val typeAccidentDao: TypeAccidentDao
): TypeAccidentRoomDatasource {

    override suspend fun addAll(list: List<TypeAccidentRoomModel>): EmptyResult =
        result(getClassAndMethod()) {
            typeAccidentDao.insertAll(list)
        }

    override suspend fun deleteAll(): EmptyResult =
        result(getClassAndMethod()) {
            typeAccidentDao.deleteAll()
        }

}