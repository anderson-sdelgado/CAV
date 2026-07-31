package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ColabRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.ColabRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ColabRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IColabRepositoryTest {

    private val colabRoomDatasource = mock<ColabRoomDatasource>()
    private val colabRetrofitDatasource = mock<ColabRetrofitDatasource>()
    private val repository = IColabRepository(
        colabRetrofitDatasource = colabRetrofitDatasource,
        colabRoomDatasource = colabRoomDatasource
    )

    @Test
    fun `addAll - Check return failure if have error`() =
        runTest {
            val roomModelList = listOf(
                ColabRoomModel(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                )
            )
            val entityList = listOf(
                Colab(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                )
            )
            whenever(
                colabRoomDatasource.addAll(roomModelList)
            ).thenReturn(
                resultFailure(
                    "IColabRoomDatasource.addAll",
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
                "IColabRepository.addAll -> IColabRoomDatasource.addAll"
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
                ColabRoomModel(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                )
            )
            val entityList = listOf(
                Colab(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                )
            )
            whenever(
                colabRoomDatasource.addAll(roomModelList)
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
                colabRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "IColabRoomDatasource.deleteAll",
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
                "IColabRepository.deleteAll -> IColabRoomDatasource.deleteAll"
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
                colabRoomDatasource.deleteAll()
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
                colabRetrofitDatasource.listAll("token")
            ).thenReturn(
                resultFailure(
                    "IColabRetrofitDatasource.listAll",
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
                "IColabRepository.listAll -> IColabRetrofitDatasource.listAll"
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
                ColabRetrofitModel(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                ),
                ColabRetrofitModel(
                    reg = 67890L,
                    name = "JOSE APARECIDO"
                )
            )
            val entityList = listOf(
                Colab(
                    reg = 12345L,
                    name = "ANDERSON DA SILVA"
                ),
                Colab(
                    reg = 67890L,
                    name = "JOSE APARECIDO"
                )
            )
            whenever(
                colabRetrofitDatasource.listAll("token")
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
    fun `hasReg - Check return failure if have error in ColabRoomDatasource hasReg`() =
        runTest {
            whenever(
                colabRoomDatasource.hasReg(19759)
            ).thenReturn(
                resultFailure(
                    "IColabRoomDatasource.hasReg",
                    "-",
                    Exception()
                )
            )
            val result = repository.hasReg(19759)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IColabRepository.hasReg -> IColabRoomDatasource.hasReg"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `hasReg - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                colabRoomDatasource.hasReg(19759)
            ).thenReturn(
                Result.success(false)
            )
            val result = repository.hasReg(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `getNameByReg - Check return failure if have error in ColabRoomDatasourceDatasource getNameByReg`() =
        runTest {
            whenever(
                colabRoomDatasource.getNameByReg(19759)
            ).thenReturn(
                resultFailure(
                    "IColabRoomDatasourceDatasource.getNameByReg",
                    "-",
                    Exception()
                )
            )
            val result = repository.getNameByReg(19759)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IColabRepository.getNameByReg -> IColabRoomDatasourceDatasource.getNameByReg"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getNameByReg - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                colabRoomDatasource.getNameByReg(19759)
            ).thenReturn(
                Result.success("ANDERSON DA SILVA DELGADO")
            )
            val result = repository.getNameByReg(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "ANDERSON DA SILVA DELGADO"
            )
        }

    @Test
    fun `listColabByRegList - Check return failure if have error in ColabRoomDatasource`() =
        runTest {
            whenever(
                colabRoomDatasource.listColabByRegList(listOf(1L))
            ).thenReturn(
                resultFailure(
                    "IColabRoomDatasource.listColabByRegList",
                    "-",
                    Exception()
                )
            )
            val result = repository.listColabByRegList(listOf(1L))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IColabRepository.listColabByRegList -> IColabRoomDatasource.listColabByRegList"
            )
        }

    @Test
    fun `listColabByRegList - Check return correct if function execute successfully`() =
        runTest {
            val roomList = listOf(ColabRoomModel(reg = 1L, name = "TEST"))
            val entityList = listOf(Colab(reg = 1L, name = "TEST"))
            whenever(
                colabRoomDatasource.listColabByRegList(listOf(1L))
            ).thenReturn(
                Result.success(roomList)
            )
            val result = repository.listColabByRegList(listOf(1L))
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