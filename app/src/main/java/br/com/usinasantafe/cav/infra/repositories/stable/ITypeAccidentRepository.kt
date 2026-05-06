package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.TypeAccidentRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.TypeAccidentRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.getOrThrow

class ITypeAccidentRepository @Inject constructor(
    private val typeAccidentRetrofitDatasource: TypeAccidentRetrofitDatasource,
    private val typeAccidentRoomDatasource: TypeAccidentRoomDatasource
): TypeAccidentRepository {

    override suspend fun addAll(list: List<TypeAccident>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            typeAccidentRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAll(): EmptyResult =
        call(getClassAndMethod()) {
            typeAccidentRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun listAll(token: String): Result<List<TypeAccident>> =
        call(getClassAndMethod()) {
            val modelList = typeAccidentRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

}