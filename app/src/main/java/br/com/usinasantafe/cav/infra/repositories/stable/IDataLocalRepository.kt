package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.DataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.OptionDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.DataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.OptionDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.DataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.retrofitModelToEntity
import br.com.usinasantafe.cav.infra.models.room.stable.entityToRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.roomModelToEntity
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.getOrThrow

class IDataLocalRepository @Inject constructor(
    private val itemDataLocalRetrofitDatasource: ItemDataLocalRetrofitDatasource,
    private val optionDataLocalRetrofitDatasource: OptionDataLocalRetrofitDatasource,
    private val dataLocalRetrofitDatasource: DataLocalRetrofitDatasource,
    private val itemDataLocalRoomDatasource: ItemDataLocalRoomDatasource,
    private val optionDataLocalRoomDatasource: OptionDataLocalRoomDatasource,
    private val dataLocalRoomDatasource: DataLocalRoomDatasource
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

    override suspend fun addAllDataLocal(list: List<DataLocal>): EmptyResult =
        call(getClassAndMethod()) {
            val roomModelList = list.map { it.entityToRoomModel() }
            dataLocalRoomDatasource.addAll(roomModelList).getOrThrow()
        }

    override suspend fun deleteAllItem(): EmptyResult =
        call(getClassAndMethod()) {
            itemDataLocalRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun deleteAllOption(): EmptyResult =
        call(getClassAndMethod()) {
            optionDataLocalRoomDatasource.deleteAll().getOrThrow()
        }

    override suspend fun deleteAllDataLocal(): EmptyResult =
        call(getClassAndMethod()) {
            dataLocalRoomDatasource.deleteAll().getOrThrow()
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

    override suspend fun listAllDataLocal(token: String): Result<List<DataLocal>> =
        call(getClassAndMethod()) {
            val modelList = dataLocalRetrofitDatasource.listAll(token).getOrThrow()
            modelList.map { it.retrofitModelToEntity() }
        }

    override suspend fun getROptionItemById(id: Int): Result<DataLocal> =
        call(getClassAndMethod()) {
            dataLocalRoomDatasource.getById(id).getOrThrow().roomModelToEntity()
        }

    override suspend fun getDescItemById(id: Int): Result<String> =
        call(getClassAndMethod()) {
            itemDataLocalRoomDatasource.getDescById(id).getOrThrow()
        }

    override suspend fun getDescOptionById(id: Int): Result<String> =
        call(getClassAndMethod()) {
            optionDataLocalRoomDatasource.getDescById(id).getOrThrow()
        }

    override suspend fun listAllOption(): Result<List<OptionDataLocal>> =
        call(getClassAndMethod()) {
            val modelList = optionDataLocalRoomDatasource.listAll().getOrThrow()
            modelList.map { it.roomModelToEntity() }
        }

    override suspend fun listDataLocalByIdOption(id: Int): Result<List<DataLocal>> =
        call(getClassAndMethod()) {
            val modelList = dataLocalRoomDatasource.listByIdOption(id).getOrThrow()
            modelList.map { it.roomModelToEntity() }
        }

    override suspend fun listItemByIdList(idList: List<Int>): Result<List<ItemDataLocal>> =
        call(getClassAndMethod()) {
            val modelList = itemDataLocalRoomDatasource.listByIdList(idList).getOrThrow()
            modelList.map { it.roomModelToEntity() }
        }

}