package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetColabTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetColab(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase(
                regColab = "de25",
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetColab -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"de25\""
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setRegColab`() =
        runTest {
            whenever(
                cardRepository.setRegColab(123456)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setRegColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                regColab = "123456",
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce())
                .setRegColab(123456)
            verify(cardRepository, never())
                .updateRegColab(123456, 0)
            verify(cardRepository, never())
                .updateRegPassengerColab(123456, 0, 0)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetColab -> ICardRepository.setRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }
    
    @Test
    fun `Check return correct if CardRepository setRegColab execute successfully`() =
        runTest {
            val result = usecase(
                regColab = "123456",
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce())
                .setRegColab(123456)
            verify(cardRepository, never())
                .updateRegColab(123456, 0)
            verify(cardRepository, never())
                .updateRegPassengerColab(123456, 0, 0)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateRegColab`() =
        runTest {
            whenever(
                cardRepository.updateRegColab(123456, 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateRegColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                regColab = "123456",
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .setRegColab(123456)
            verify(cardRepository, atLeastOnce())
                .updateRegColab(123456, 1)
            verify(cardRepository, never())
                .updateRegPassengerColab(123456, 1, 0)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetColab -> ICardRepository.updateRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if CardRepository updateRegColab execute successfully`() =
        runTest {
            val result = usecase(
                regColab = "123456",
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .setRegColab(123456)
            verify(cardRepository, atLeastOnce())
                .updateRegColab(123456, 1)
            verify(cardRepository, never())
                .updateRegPassengerColab(123456, 1, 0)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateRegPassengerColab`() =
        runTest {
            whenever(
                cardRepository.updateRegPassengerColab(123456, 1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateRegPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                regColab = "123456",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            verify(cardRepository, never())
                .setRegColab(123456)
            verify(cardRepository, never())
                .updateRegColab(123456, 1)
            verify(cardRepository, atLeastOnce())
                .updateRegPassengerColab(123456, 1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetColab -> ICardRepository.updateRegPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase(
                regColab = "123456",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            verify(cardRepository, never())
                .setRegColab(123456)
            verify(cardRepository, never())
                .updateRegColab(123456, 1)
            verify(cardRepository, atLeastOnce())
                .updateRegPassengerColab(123456, 1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}