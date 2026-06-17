package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetStateTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetState(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setStateColab - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.setStateColab(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
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
                "ISetState -> ICardRepository.setStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote COLAB`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateColab(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setStateColab - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.setStateColab(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.setStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateColab(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setStateInvolved - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.setStateInvolved(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.setStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote DRIVER`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateInvolved(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setStateInvolved - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setStateInvolved(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.setStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateInvolved(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setStateInvolved - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setStateInvolved(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.setStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateInvolved(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setStateInvolved - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.setStateInvolved(State.INJURED)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.setStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote WITNESS`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setStateInvolved(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStateColab - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.updateStateColab(State.INJURED, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStateColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote COLAB`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateStateColab(State.INJURED, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStatePassengerColab - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.updateStatePassengerColab(State.INJURED, 10, 20)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStatePassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 20
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStatePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 10,
                idSecondary = 20
            )
            verify(cardRepository, atLeastOnce()).updateStatePassengerColab(State.INJURED, 10, 20)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStateDriver - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.updateStateDriver(State.INJURED, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStateDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStateDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote DRIVER`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateStateDriver(State.INJURED, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStatePassengerInvolved - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.updateStatePassengerInvolved(State.INJURED, 10, 20)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStatePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 10,
                idSecondary = 20
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStatePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 10,
                idSecondary = 20
            )
            verify(cardRepository, atLeastOnce()).updateStatePassengerInvolved(State.INJURED, 10, 20)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStateInvolved - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.updateStateInvolved(State.INJURED, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateStateInvolved(State.INJURED, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updateStateWitness - Option EDIT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.updateStateWitness(State.INJURED, 10)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateStateWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 10,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetState -> ICardRepository.updateStateWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote WITNESS`() =
        runTest {
            val result = usecase(
                state = State.INJURED,
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 10,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateStateWitness(State.INJURED, 10)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
