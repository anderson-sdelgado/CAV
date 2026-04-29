package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.EquipRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.EquipRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.getOrThrow

class IEquipRepository @Inject constructor(
    private val equipRetrofitDatasource: EquipRetrofitDatasource,
    private val equipRoomDatasource: EquipRoomDatasource
): EquipRepository {

    override suspend fun addAll(list: List<Equip>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            equipRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAll(): EmptyResult =
        call(getClassAndMethod()) {
            equipRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun listAll(token: String): Result<List<Equip>> =
        call(getClassAndMethod()) {
            val modelList = equipRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

    override suspend fun hasNro(nro: Long): Result<Boolean> =
        call(getClassAndMethod()) {
            equipRoomDatasource.hasNro(nro).getOrThrow()
        }

    override suspend fun getIdByNro(nro: Long): Result<Int> =
        call(getClassAndMethod()) {
            equipRoomDatasource.getIdByNro(nro).getOrThrow()
        }

}