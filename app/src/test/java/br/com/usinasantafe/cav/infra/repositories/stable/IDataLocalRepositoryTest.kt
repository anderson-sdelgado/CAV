package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.OptionDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ROptionItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.OptionDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ROptionItemDataLocalRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ItemDataLocalRetrofitModel
import br.com.usinasantafe.cav.infra.models.retrofit.stable.OptionDataLocalRetrofitModel
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ROptionItemDataLocalRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IDataLocalRepositoryTest {

    private val itemDataLocalRetrofitDatasource = mock<ItemDataLocalRetrofitDatasource>()
    private val optionDataLocalRetrofitDatasource = mock<OptionDataLocalRetrofitDatasource>()
    private val rOptionItemDataLocalRetrofitDatasource = mock<ROptionItemDataLocalRetrofitDatasource>()
    private val itemDataLocalRoomDatasource = mock<ItemDataLocalRoomDatasource>()
    private val optionDataLocalRoomDatasource = mock<OptionDataLocalRoomDatasource>()
    private val rOptionItemDataLocalRoomDatasource = mock<ROptionItemDataLocalRoomDatasource>()
    private val repository = IDataLocalRepository(
        itemDataLocalRetrofitDatasource = itemDataLocalRetrofitDatasource,
        optionDataLocalRetrofitDatasource = optionDataLocalRetrofitDatasource,
        rOptionItemDataLocalRetrofitDatasource = rOptionItemDataLocalRetrofitDatasource,
        itemDataLocalRoomDatasource = itemDataLocalRoomDatasource,
        optionDataLocalRoomDatasource = optionDataLocalRoomDatasource,
        rOptionItemDataLocalRoomDatasource = rOptionItemDataLocalRoomDatasource
    )

    @Test
    fun `addAllItem - Check return failure if have error in ItemDataLocalRoomDatasource addAll`() =
        runTest {
            val modelList = listOf(
                ItemDataLocalRoomModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                ItemDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            whenever(
                itemDataLocalRoomDatasource.addAll(modelList)
            ).thenReturn(
                resultFailure(
                    "IItemRoomDatasource.addAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.addAllItem(entityList)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.addAllItem -> IItemRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `addAllItem - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                ItemDataLocalRoomModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                ItemDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            val result = repository.addAllItem(entityList)
            verify(itemDataLocalRoomDatasource, atLeastOnce()).addAll(modelList)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `addAllOption - Check return failure if have error in OptionDataLocalRoomDatasource addAll`() =
        runTest {
            val modelList = listOf(
                OptionDataLocalRoomModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                OptionDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            whenever(
                optionDataLocalRoomDatasource.addAll(modelList)
            ).thenReturn(
                resultFailure(
                    "IOptionRoomDatasource.addAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.addAllOption(entityList)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.addAllOption -> IOptionRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `addAllOption - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                OptionDataLocalRoomModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                OptionDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            val result = repository.addAllOption(entityList)
            verify(optionDataLocalRoomDatasource, atLeastOnce()).addAll(modelList)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `addAllROptionItem - Check return failure if have error in ROptionItemDataLocalRoomDatasource addAll`() =
        runTest {
            val modelList = listOf(
                ROptionItemDataLocalRoomModel(
                    id = 1,
                    idOption = 1,
                    idItem = 1
                )
            )
            val entityList = listOf(
                ROptionItemDataLocal(
                    id = 1,
                    idOption = 1,
                    idItem = 1
                )
            )
            whenever(
                rOptionItemDataLocalRoomDatasource.addAll(modelList)
            ).thenReturn(
                resultFailure(
                    "IROptionItemDataLocalRoomDatasource.addAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.addAllROptionItem(entityList)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.addAllROptionItem -> IROptionItemDataLocalRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `addAllROptionItem - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                ROptionItemDataLocalRoomModel(
                    id = 1,
                    idOption = 1,
                    idItem = 1
                )
            )
            val entityList = listOf(
                ROptionItemDataLocal(
                    id = 1,
                    idOption = 1,
                    idItem = 1
                )
            )
            val result = repository.addAllROptionItem(entityList)
            verify(rOptionItemDataLocalRoomDatasource, atLeastOnce()).addAll(modelList)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deleteAllItem - Check return failure if have error in ItemDataLocalRoomDatasource deleteAll`() =
        runTest {
            whenever(
                itemDataLocalRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "IItemDataLocalRoomDatasource.deleteAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteAllItem()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.deleteAllItem -> IItemDataLocalRoomDatasource.deleteAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteAllItem - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteAllItem()
            verify(itemDataLocalRoomDatasource, atLeastOnce()).deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Unit
            )
        }

    @Test
    fun `deleteAllOption - Check return failure if have error in OptionDataLocalRoomDatasource deleteAll`() =
        runTest {
            whenever(
                optionDataLocalRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "IOptionDataLocalRoomDatasource.deleteAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteAllOption()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.deleteAllOption -> IOptionDataLocalRoomDatasource.deleteAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteAllOption - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteAllOption()
            verify(optionDataLocalRoomDatasource, atLeastOnce()).deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Unit
            )
        }

    @Test
    fun `deleteAllROptionItem - Check return failure if have error in ROptionItemDataLocalRoomDatasource deleteAll`() =
        runTest {
            whenever(
                rOptionItemDataLocalRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "IROptionItemDataLocalRoomDatasource.deleteAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteAllROptionItem()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.deleteAllROptionItem -> IROptionItemDataLocalRoomDatasource.deleteAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteAllROptionItem - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteAllROptionItem()
            verify(rOptionItemDataLocalRoomDatasource, atLeastOnce()).deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Unit
            )
        }

    @Test
    fun `listAllItem - Check return failure if have error in ItemDataLocalRetrofitDatasource listAll`() =
        runTest {
            whenever(
                itemDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                resultFailure(
                    "IItemDataLocalRetrofitDatasource.listAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.listAllItem("TOKEN")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.listAllItem -> IItemDataLocalRetrofitDatasource.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listAllItem - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                ItemDataLocalRetrofitModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                ItemDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            whenever(
                itemDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                Result.success(modelList)
            )
            val result = repository.listAllItem("TOKEN")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                entityList
            )
        }

    @Test
    fun `listAllOption - Check return failure if have error in OptionDataLocalRetrofitDatasource listAll`() =
        runTest {
            whenever(
                optionDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                resultFailure(
                    "IOptionDataLocalRetrofitDatasource.listAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.listAllOption("TOKEN")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.listAllOption -> IOptionDataLocalRetrofitDatasource.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listAllOption - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                OptionDataLocalRetrofitModel(
                    id = 1,
                    desc = "Test"
                )
            )
            val entityList = listOf(
                OptionDataLocal(
                    id = 1,
                    desc = "Test"
                )
            )
            whenever(
                optionDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                Result.success(modelList)
            )
            val result = repository.listAllOption("TOKEN")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                entityList
            )
        }

    @Test
    fun `listAllROptionItem - Check return failure if have error in ROptionItemRetrofitDatasource listAll`() =
        runTest {
            whenever(
                rOptionItemDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                resultFailure(
                    "IROptionItemRetrofitDatasource.listAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.listAllROptionItem("TOKEN")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDataLocalRepository.listAllROptionItem -> IROptionItemRetrofitDatasource.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listAllROptionItem - Check return correct if function execute successfully`() =
        runTest {
            val modelList = listOf(
                ROptionItemDataLocalRetrofitModel(
                    id = 1,
                    idItem = 1,
                    idOption = 1
                )
            )
            val entityList = listOf(
                ROptionItemDataLocal(
                    id = 1,
                    idItem = 1,
                    idOption = 1
                )
            )
            whenever(
                rOptionItemDataLocalRetrofitDatasource.listAll("TOKEN")
            ).thenReturn(
                Result.success(modelList)
            )
            val result = repository.listAllROptionItem("TOKEN")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                entityList
            )
        }

}