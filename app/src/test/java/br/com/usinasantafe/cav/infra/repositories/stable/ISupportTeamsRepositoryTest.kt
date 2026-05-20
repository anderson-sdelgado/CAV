package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.SupportTeamsRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.SupportTeamsRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.SupportTeamsRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISupportTeamsRepositoryTest {

    private val supportTeamsRoomDatasource = mock<SupportTeamsRoomDatasource>()
    private val supportTeamsRetrofitDatasource = mock<SupportTeamsRetrofitDatasource>()
    private val repository = ISupportTeamsRepository(
        supportTeamsRetrofitDatasource = supportTeamsRetrofitDatasource,
        supportTeamsRoomDatasource = supportTeamsRoomDatasource
    )

    @Test
    fun `addAll - Check return failure if have error`() =
        runTest {
            val roomModelList = listOf(
                SupportTeamsRoomModel(
                    id = 1,
                    description = "TEST"
                )
            )
            val entityList = listOf(
                SupportTeams(
                    id = 1,
                    description = "TEST"
                )
            )
            whenever(
                supportTeamsRoomDatasource.addAll(roomModelList)
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRoomDatasource.addAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.addAll(entityList)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRepository.addAll -> ISupportTeamsRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `addAll - Check return true if function execute successfully`() =
        runTest {
            val roomModelList = listOf(
                SupportTeamsRoomModel(
                    id = 1,
                    description = "TEST"
                )
            )
            val entityList = listOf(
                SupportTeams(
                    id = 1,
                    description = "TEST"
                )
            )
            whenever(
                supportTeamsRoomDatasource.addAll(roomModelList)
            ).thenReturn(
                Result.success(Unit)
            )
            val result = repository.addAll(entityList)
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
    fun `deleteAll - Check return failure if have error`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRoomDatasource.deleteAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteAll()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRepository.deleteAll -> ISupportTeamsRoomDatasource.deleteAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteAll - Check return true if function execute successfully`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.deleteAll()
            ).thenReturn(
                Result.success(Unit)
            )
            val result = repository.deleteAll()
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
    fun `listAll - Check return failure if have error`() =
        runTest {
            whenever(
                supportTeamsRetrofitDatasource.listAll("token")
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRetrofitDatasource.listAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.listAll("token")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRepository.listAll -> ISupportTeamsRetrofitDatasource.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listAll - Check return true if function execute successfully`() =
        runTest {
            val retrofitModelList = listOf(
                SupportTeamsRetrofitModel(
                    id = 1,
                    desc = "TEST"
                ),
            )
            val entityList = listOf(
                SupportTeams(
                    id = 1,
                    description = "TEST"
                ),
            )
            whenever(
                supportTeamsRetrofitDatasource.listAll("token")
            ).thenReturn(
                Result.success(
                    retrofitModelList
                )
            )
            val result = repository.listAll("token")
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
    fun `listAll - Check return failure if have error in SupportTeamsRoomDatasource listAll`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.listAll()
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRoomDatasource.listAll",
                    "-",
                    Exception()
                )
            )
            val result = repository.listAll()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRepository.listAll -> ISupportTeamsRoomDatasource.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listAll - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        SupportTeamsRoomModel(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            val result = repository.listAll()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    SupportTeams(
                        id = 1,
                        description = "Test"
                    )
                )
            )
        }

    @Test
    fun `listByIdList - Check return failure if have error in SupportTeamsRoomDatasource listByIdList`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.listByIdList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRoomDatasource.listByIdList",
                    "-",
                    Exception()
                )
            )
            val result = repository.listByIdList(listOf(1, 2))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRepository.listByIdList -> ISupportTeamsRoomDatasource.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listByIdList - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                supportTeamsRoomDatasource.listByIdList(listOf(1, 2))
            ).thenReturn(
                Result.success(
                    listOf(
                        SupportTeamsRoomModel(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            val result = repository.listByIdList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    SupportTeams(
                        id = 1,
                        description = "Test"
                    )
                )
            )
        }

}