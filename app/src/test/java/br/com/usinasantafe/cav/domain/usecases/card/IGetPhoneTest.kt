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

class IGetPhoneTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetPhone(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getPhoneInvolved - Option INSERT`() =
        runTest {
            whenever(
                cardRepository.getPhoneInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPhoneInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetPhone -> ICardRepository.getPhoneInvolved"
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
                cardRepository.getPhoneInvolved()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
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
                cardRepository.getPhoneInvolved()
            ).thenReturn(
                Result.success("(16) 99999-1234")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "(16) 99999-1234"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getPhoneDriver - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getPhoneDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPhoneDriver",
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
                "IGetPhone -> ICardRepository.getPhoneDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getPhoneDriver(1)
            ).thenReturn(
                Result.success<String?>(null)
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
                cardRepository.getPhoneDriver(1)
            ).thenReturn(
                Result.success("16999999999")
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
                "16999999999"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getPhoneInvolved - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getPhoneInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPhoneInvolved",
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
                "IGetPhone -> ICardRepository.getPhoneInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getPhoneInvolved(1)
            ).thenReturn(
                Result.success<String?>(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
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
                cardRepository.getPhoneInvolved(1)
            ).thenReturn(
                Result.success("16999999999")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "16999999999"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getPhoneWitness - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getPhoneWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPhoneWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetPhone -> ICardRepository.getPhoneWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getPhoneWitness(1)
            ).thenReturn(
                Result.success<String?>(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
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
                cardRepository.getPhoneWitness(1)
            ).thenReturn(
                Result.success("16999999999")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "16999999999"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getPhonePassengerInvolved - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getPhonePassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPhonePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetPhone -> ICardRepository.getPhonePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getPhonePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success<String?>(null)
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
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
                cardRepository.getPhonePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("16999999999")
            )
            val result = usecase(
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "16999999999"
            )
        }

}
