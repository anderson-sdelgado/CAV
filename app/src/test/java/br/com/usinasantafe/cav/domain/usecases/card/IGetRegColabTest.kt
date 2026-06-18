package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetRegColabTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetRegColab(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getRegColab - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getRegColab()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getRegColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetRegColab -> ICardRepository.getRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and return null - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getRegColab()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getRegColab()
            ).thenReturn(
                Result.success(123456)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123456"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getRegColab - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getRegColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetRegColab -> ICardRepository.getRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegColab(1)
            ).thenReturn(
                Result.success(123456)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123456"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getRegPassengerColab - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegPassengerColab(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getRegPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetRegColab -> ICardRepository.getRegPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegPassengerColab(1, 2)
            ).thenReturn(
                Result.success(456789)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "456789"
            )
        }

}