package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.SupportTeamsRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.SupportTeamsRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.getOrThrow

class ISupportTeamsRepository @Inject constructor(
    private val supportTeamsRetrofitDatasource: SupportTeamsRetrofitDatasource,
    private val supportTeamsRoomDatasource: SupportTeamsRoomDatasource
): SupportTeamsRepository {

    override suspend fun addAll(list: List<SupportTeams>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            supportTeamsRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAll(): EmptyResult =
        call(getClassAndMethod()) {
            supportTeamsRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun listAll(token: String): Result<List<SupportTeams>> =
        call(getClassAndMethod()) {
            val modelList = supportTeamsRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

}