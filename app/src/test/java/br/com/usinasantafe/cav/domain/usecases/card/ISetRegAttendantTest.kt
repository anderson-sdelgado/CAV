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

class ISetRegAttendantTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetRegAttendant(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase("19759a")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetRegAttendant -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"19759a\""
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setRegAttendant`() =
        runTest {
            whenever(
                cardRepository.setRegAttendant(19759)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setRegAttendant",
                    "-",
                    Exception()
                )
            )
            val result = usecase("19759")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetRegAttendant -> ICardRepository.setRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase("19759")
            verify(cardRepository, atLeastOnce()).setRegAttendant(19759)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}