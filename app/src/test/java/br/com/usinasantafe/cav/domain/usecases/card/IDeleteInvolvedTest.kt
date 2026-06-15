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

class IDeleteInvolvedTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IDeleteInvolved(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository deleteInvolved`() =
        runTest {
            whenever(
                cardRepository.deleteInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deleteInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteInvolved -> ICardRepository.deleteInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase(1)
            verify(cardRepository, atLeastOnce()).deleteInvolved(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
