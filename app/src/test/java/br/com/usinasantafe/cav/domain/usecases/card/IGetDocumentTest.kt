package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDocumentTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetDocument(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getDocumentDriver - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDocumentDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDocument -> ICardRepository.getDocumentDriver"
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
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
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
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                Result.success("123456")
            )
            val result = usecase(
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
                "123456"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getDocumentInvolved - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDocumentInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDocumentInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDocument -> ICardRepository.getDocumentInvolved"
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
                cardRepository.getDocumentInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
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
                cardRepository.getDocumentInvolved(1)
            ).thenReturn(
                Result.success("123456")
            )
            val result = usecase(
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
                "123456"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getDocumentWitness - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDocumentWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDocumentWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDocument -> ICardRepository.getDocumentWitness"
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
                cardRepository.getDocumentWitness(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
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
                cardRepository.getDocumentWitness(1)
            ).thenReturn(
                Result.success("123456")
            )
            val result = usecase(
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
                "123456"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getDocumentPassengerInvolved - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDocumentPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDocument -> ICardRepository.getDocumentPassengerInvolved"
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
                cardRepository.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
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
                cardRepository.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("123456")
            )
            val result = usecase(
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
                "123456"
            )
        }

}
