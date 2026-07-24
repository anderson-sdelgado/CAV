package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IStartFlowTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IStartFlow(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository delete`() =
        runTest {
            whenever(
                cardRepository.delete()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.delete",
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
                "IStartFlow -> ICardRepository.delete"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository has`() =
        runTest {
            whenever(
                cardRepository.delete()
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                cardRepository.has()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.has",
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
                "IStartFlow -> ICardRepository.has"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return true if function execute successfully and have data in database`() =
        runTest {
            whenever(
                cardRepository.delete()
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                cardRepository.has()
            ).thenReturn(
                Result.success(true)
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).delete()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun `Check return false if function execute successfully and not have data in database`() =
        runTest {
            whenever(
                cardRepository.delete()
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                cardRepository.has()
            ).thenReturn(
                Result.success(false)
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).delete()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

}
