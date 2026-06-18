package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetStateTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetState(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getIdStateColab - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getStateColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStateColab",
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
                "IGetIdState -> ICardRepository.getIdStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getStateColab(1)
            ).thenReturn(
                Result.success(State.INJURED)
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
                State.INJURED
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdStatePassengerColab - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getStatePassengerColab(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStatePassengerColab",
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
                "IGetIdState -> ICardRepository.getIdStatePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getStatePassengerColab(1, 2)
            ).thenReturn(
                Result.success(State.INJURED)
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
                State.INJURED
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdStateDriver - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getStateDriver(2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStateDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 2,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetIdState -> ICardRepository.getIdStateDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getStateDriver(1)
            ).thenReturn(
                Result.success(State.INJURED)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.INJURED
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdStatePassengerInvolved - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getStatePassengerInvolved(1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStatePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetIdState -> ICardRepository.getIdStatePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getStatePassengerInvolved(1, 1)
            ).thenReturn(
                Result.success(State.INJURED)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 1
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.INJURED
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdStateInvolved - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getStateInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetIdState -> ICardRepository.getIdStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getStateInvolved(3)
            ).thenReturn(
                Result.success(State.INJURED)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 3,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.INJURED
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdStateWitness - Option EDIT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getStateWitness(2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdStateWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 2,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetIdState -> ICardRepository.getIdStateWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getStateWitness(3)
            ).thenReturn(
                Result.success(State.INJURED)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 3,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.INJURED
            )
        }

}