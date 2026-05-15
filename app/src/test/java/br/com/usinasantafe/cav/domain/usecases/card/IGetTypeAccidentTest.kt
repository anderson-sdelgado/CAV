package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetTypeAccidentTest {

    private val cardRepository = mock<CardRepository>()
    private val typeAccidentRepository = mock<TypeAccidentRepository>()
    private val usecase = IGetTypeAccident(
        cardRepository = cardRepository,
        typeAccidentRepository = typeAccidentRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdTypeAccident`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdTypeAccident",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetTypeAccident -> ICardRepository.listIdTypeAccident"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in TypeAccidentRepository listByIdList`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                typeAccidentRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ITypeAccidentRepository.listByIdList",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetTypeAccident -> ITypeAccidentRepository.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if listIdTypeAccident return null and listByIdList return null`() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetTypeAccident -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return failure if listIdTypeAccident return emptyList and listByIdList return null`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetTypeAccident -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return correct if listIdTypeAccident return emptyList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                typeAccidentRepository.listByIdList(emptyList())
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "-"
            )
        }

    @Test
    fun `Check return correct if listIdTypeAccident return idList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                typeAccidentRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "-"
            )
        }

    @Test
    fun `Check return correct if listIdTypeAccident return idList and listByIdList return list of data`() =
        runTest {
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                typeAccidentRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                Result.success(
                    listOf(
                        TypeAccident(
                            id = 1,
                            description = "Item 1"
                        ),
                        TypeAccident(
                            id = 2,
                            description = "Item 2"
                        )
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Item 1 - Item 2"
            )
        }
}