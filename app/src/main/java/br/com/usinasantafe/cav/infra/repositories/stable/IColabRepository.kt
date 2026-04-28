package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ColabRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.collections.addAll

class IColabRepository @Inject constructor(
    private val colabRetrofitDatasource: ColabRetrofitDatasource,
    private val colabRoomDatasource: ColabRoomDatasource
): ColabRepository {

    override suspend fun addAll(list: List<Colab>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            colabRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAll(): EmptyResult =
        call(getClassAndMethod()) {
            colabRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun listAll(token: String): Result<List<Colab>> =
        call(getClassAndMethod()) {
            val modelList = colabRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

    override suspend fun hasReg(reg: Long): Result<Boolean> =
        call(getClassAndMethod()) {
            colabRoomDatasource.hasReg(reg).getOrThrow()
        }

}