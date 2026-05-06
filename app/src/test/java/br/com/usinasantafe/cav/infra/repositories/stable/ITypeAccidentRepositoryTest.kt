package br.com.usinasantafe.cav.infra.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.TypeAccidentRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.stable.TypeAccidentRoomDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.TypeAccidentRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ITypeAccidentRepositoryTest {

    private val typeAccidentRoomDatasource = mock<TypeAccidentRoomDatasource>()
    private val typeAccidentRetrofitDatasource = mock<TypeAccidentRetrofitDatasource>()
    private val repository = ITypeAccidentRepository(
        typeAccidentRetrofitDatasource = typeAccidentRetrofitDatasource,
        typeAccidentRoomDatasource = typeAccidentRoomDatasource
    )

    @Test
    fun `addAll - Check return failure if have error`() =
        runTest {
            val roomModelList = listOf(
                TypeAccidentRoomModel(
                    id = 1,
                    desc = "TEST"
                )
            )
            val entityList = listOf(
                TypeAccident(
                    id = 1,
                    desc = "TEST"
                )
            )
            whenever(
                typeAccidentRoomDatasource.addAll(roomModelList)
            ).thenReturn(
                resultFailure(
                    "ITypeAccidentRoomDatasource.addAll",
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
                "ITypeAccidentRepository.addAll -> ITypeAccidentRoomDatasource.addAll"
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
                TypeAccidentRoomModel(
                    id = 1,
                    desc = "TEST"
                )
            )
            val entityList = listOf(
                TypeAccident(
                    id = 1,
                    desc = "TEST"
                )
            )
            whenever(
                typeAccidentRoomDatasource.addAll(roomModelList)
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
                typeAccidentRoomDatasource.deleteAll()
            ).thenReturn(
                resultFailure(
                    "ITypeAccidentRoomDatasource.deleteAll",
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
                "ITypeAccidentRepository.deleteAll -> ITypeAccidentRoomDatasource.deleteAll"
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
                typeAccidentRoomDatasource.deleteAll()
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
                typeAccidentRetrofitDatasource.listAll("token")
            ).thenReturn(
                resultFailure(
                    "ITypeAccidentRetrofitDatasource.listAll",
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
                "ITypeAccidentRepository.listAll -> ITypeAccidentRetrofitDatasource.listAll"
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
                TypeAccidentRetrofitModel(
                    id = 1,
                    desc = "TEST"
                ),
            )
            val entityList = listOf(
                TypeAccident(
                    id = 1,
                    desc = "TEST"
                ),
            )
            whenever(
                typeAccidentRetrofitDatasource.listAll("token")
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