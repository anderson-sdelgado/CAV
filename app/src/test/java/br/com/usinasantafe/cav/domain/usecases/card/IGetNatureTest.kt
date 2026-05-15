package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetNatureTest {

    private val cardRepository = mock<CardRepository>()
    private val natureRepository = mock<NatureRepository>()
    private val usecase = IGetNature(
        cardRepository = cardRepository,
        natureRepository = natureRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdNature`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdNature",
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
                "IGetNature -> ICardRepository.listIdNature"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in NatureRepository listByIdList`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                natureRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "INatureRepository.listByIdList",
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
                "IGetNature -> INatureRepository.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if listIdNature return null and listByIdList return null`() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetNature -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return failure if listIdNature return emptyList and listByIdList return null`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
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
                "IGetNature -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return correct if listIdNature return emptyList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                natureRepository.listByIdList(emptyList())
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
    fun `Check return correct if listIdNature return idList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                natureRepository.listByIdList(listOf(1, 2))
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
    fun `Check return correct if listIdNature return idList and listByIdList return list of data`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                natureRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                Result.success(
                    listOf(
                        Nature(
                            id = 1,
                            description = "Item 1"
                        ),
                        Nature(
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