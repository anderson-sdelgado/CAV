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

class ISetObsTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetObs(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setObs`() =
        runTest {
            whenever(
                cardRepository.setObs("TESTE OBS")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setObs",
                    "-",
                    Exception()
                )
            )
            val result = usecase("TESTE OBS")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetObs -> ICardRepository.setObs"
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
                cardRepository.setObs("TESTE OBS")
            ).thenReturn(
                Result.success(Unit)
            )
            val result = usecase("TESTE OBS")
            verify(cardRepository, atLeastOnce()).setObs("TESTE OBS")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
