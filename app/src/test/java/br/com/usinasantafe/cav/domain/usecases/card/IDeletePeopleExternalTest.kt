package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IDeletePeopleExternalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IDeleteInvolvedExternal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository deletePassengerColab - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.deletePassengerColab(1, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deletePassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeletePassenger -> ICardRepository.deletePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote PASSENGER_COLAB`() =
        runTest {
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 1
            )
            verify(cardRepository, atLeastOnce()).deletePassengerColab(1, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository deletePassengerInvolved - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.deletePassengerExternal(1, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deletePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                idMain = 10,
                idSecondary = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeletePassenger -> ICardRepository.deletePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            val result = usecase(
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                idMain = 10,
                idSecondary = 1
            )
            verify(cardRepository, atLeastOnce()).deletePassengerExternal(1, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
