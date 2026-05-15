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

class ICancelCardTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ICancelCard(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository clean`() =
        runTest {
            whenever(
                cardRepository.clean()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.clean",
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
                "ICancelCard -> ICardRepository.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase()
            verify(cardRepository, atLeastOnce()).clean()
            assertEquals(
                result.isSuccess,
                true
            )
        }

}