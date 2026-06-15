package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescPassengersTest {

    private val cardRepository = mock<CardRepository>()
    private val colabRepository = mock<ColabRepository>()
    private val usecase = IGetDescPassengers(
        cardRepository = cardRepository,
        colabRepository = colabRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listRegPassengerColab - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.listRegPassengerColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listRegPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescPassengers -> ICardRepository.listRegPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in ColabRepository listColabByRegList - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.listRegPassengerColab(1)
            ).thenReturn(
                Result.success(listOf(123456L, 456789L))
            )
            whenever(
                colabRepository.listColabByRegList(listOf(123456L, 456789L))
            ).thenReturn(
                resultFailure(
                    "IColabRepository.listColabByRegList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescPassengers -> IColabRepository.listColabByRegList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty string if function execute successfully with empty list - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.listRegPassengerColab(1)
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                colabRepository.listColabByRegList(emptyList())
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1
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
    fun `Check return correct formatted string if function execute successfully - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.listRegPassengerColab(1)
            ).thenReturn(
                Result.success(listOf(123456L, 456789L))
            )
            whenever(
                colabRepository.listColabByRegList(listOf(123456L, 456789L))
            ).thenReturn(
                Result.success(
                    listOf(
                        Colab(reg = 123456L, name = "COLAB 1"),
                        Colab(reg = 456789L, name = "COLAB 2")
                    )
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123456 - COLAB 1\n456789 - COLAB 2"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository listPassengerInvolved - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.listPassengerInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescPassengers -> ICardRepository.listPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty string if function execute successfully with empty list - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.listPassengerInvolved(1)
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1
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
    fun `Check return correct formatted string if function execute successfully - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.listPassengerInvolved(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        Involved(document = "123", name = "INVOLVED 1"),
                        Involved(document = null, name = "INVOLVED 2")
                    )
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123 - INVOLVED 1\n- - INVOLVED 2"
            )
        }

}
