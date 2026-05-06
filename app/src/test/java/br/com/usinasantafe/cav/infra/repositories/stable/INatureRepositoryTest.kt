package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.NatureRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.NatureRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.NatureRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class INatureRepositoryTest {

    private val natureRoomDatasource = mock<NatureRoomDatasource>()
    private val natureRetrofitDatasource = mock<NatureRetrofitDatasource>()
    private val repository = INatureRepository(
        natureRetrofitDatasource = natureRetrofitDatasource,
        natureRoomDatasource = natureRoomDatasource
    )

    @Test
    fun `addAll - Check return failure if have error`() =
        runTest {
            val roomModelList = listOf(
                NatureRoomModel(
                    id = 1,
                    desc = "TEST"
                )
            )
            val entityList = listOf(
                Nature(
                    id = 1,
                    desc = "TEST"
                )
            )
            whenever(
                natureRoomDatasource.addAll(roomModelList)
            ).thenReturn(
                resultFailure(
                    "INatureRoomDatasource.addAll",
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
                "INatureRepository.addAll -> INatureRoomDatasource.addAll"
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
                NatureRoomModel(
                    id = 1,
                    desc = "TEST"
                )
            )
            val entityList = listOf(
                Nature(
                    id = 1,
                    desc = "TEST"
                )
            )
            whenever(
                natureRoomDatasource.addAll(roomModelList)
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
                natureRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "INatureRoomDatasource.deleteAll",
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
                "INatureRepository.deleteAll -> INatureRoomDatasource.deleteAll"
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
                natureRoomDatasource.deleteAll()
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
                natureRetrofitDatasource.listAll("token")
            ).thenReturn(
                resultFailure(
                    "INatureRetrofitDatasource.listAll",
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
                "INatureRepository.listAll -> INatureRetrofitDatasource.listAll"
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
                NatureRetrofitModel(
                    id = 1,
                    desc = "TEST"
                ),
            )
            val entityList = listOf(
                Nature(
                    id = 1,
                    desc = "TEST"
                ),
            )
            whenever(
                natureRetrofitDatasource.listAll("token")
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

}