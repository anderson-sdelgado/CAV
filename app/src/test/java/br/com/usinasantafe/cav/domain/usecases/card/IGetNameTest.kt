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

class IGetNameTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetName(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getName - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getName()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getName",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetName -> ICardRepository.getName"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and return is null - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getName()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
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
                cardRepository.getName()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "Test"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getNameDriver - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getNameDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetName -> ICardRepository.getNameDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return hyphen if function execute successfully and return is null - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                Result.success(null)
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
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                Result.success("Test Name")
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
                "Test Name"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getNameInvolved - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNameInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getNameInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetName -> ICardRepository.getNameInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return hyphen if function execute successfully and return is null - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNameInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNameInvolved(1)
            ).thenReturn(
                Result.success("Test Name")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test Name"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getNameWitness - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getNameWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getNameWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetName -> ICardRepository.getNameWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return hyphen if function execute successfully and return is null - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getNameWitness(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getNameWitness(1)
            ).thenReturn(
                Result.success("Test Name")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS_EXTERNAL,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test Name"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getNamePassengerInvolved - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNamePassengerExternal(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getNamePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetName -> ICardRepository.getNamePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return hyphen if function execute successfully and return is null - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNamePassengerExternal(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getNamePassengerExternal(1, 2)
            ).thenReturn(
                Result.success("Test Name")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_EXTERNAL,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test Name"
            )
        }

}
