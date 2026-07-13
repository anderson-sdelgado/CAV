package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetObsTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetObs(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getObs`() =
        runTest {
            whenever(
                cardRepository.getObs()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getObs",
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
                "IGetObs -> ICardRepository.getObs"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.getObs()
            ).thenReturn(
                Result.success("TESTE OBS")
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).getObs()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "TESTE OBS"
            )
        }

    @Test
    fun `Check return empty string if CardRepository getObs return null`() =
        runTest {
            whenever(
                cardRepository.getObs()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).getObs()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                ""
            )
        }

}
