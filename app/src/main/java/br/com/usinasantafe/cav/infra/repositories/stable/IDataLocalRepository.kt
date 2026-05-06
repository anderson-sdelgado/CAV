package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.OptionDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ROptionItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.OptionDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ROptionItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.getOrThrow

class IDataLocalRepository @Inject constructor(
    private val itemDataLocalRetrofitDatasource: ItemDataLocalRetrofitDatasource,
    private val optionDataLocalRetrofitDatasource: OptionDataLocalRetrofitDatasource,
    private val rOptionItemDataLocalRetrofitDatasource: ROptionItemDataLocalRetrofitDatasource,
    private val itemDataLocalRoomDatasource: ItemDataLocalRoomDatasource,
    private val optionDataLocalRoomDatasource: OptionDataLocalRoomDatasource,
    private val rOptionItemDataLocalRoomDatasource: ROptionItemDataLocalRoomDatasource
): DataLocalRepository {

    override suspend fun addAllItem(list: List<ItemDataLocal>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            itemDataLocalRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun addAllOption(list: List<OptionDataLocal>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            optionDataLocalRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun addAllROptionItem(list: List<ROptionItemDataLocal>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            rOptionItemDataLocalRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAllItem(): EmptyResult =
        call(getClassAndMethod()) {
            itemDataLocalRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun deleteAllOption(): EmptyResult =
        call(getClassAndMethod()) {
            optionDataLocalRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun deleteAllROptionItem(): EmptyResult =
        call(getClassAndMethod()) {
            rOptionItemDataLocalRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun listAllItem(token: String): Result<List<ItemDataLocal>> =
        call(getClassAndMethod()) {
            val modelList = itemDataLocalRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

    override suspend fun listAllOption(token: String): Result<List<OptionDataLocal>> =
        call(getClassAndMethod()) {
            val modelList = optionDataLocalRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

    override suspend fun listAllROptionItem(token: String): Result<List<ROptionItemDataLocal>> =
        call(getClassAndMethod()) {
            val modelList = rOptionItemDataLocalRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

}