package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescColabTest {

    private val cardRepository = mock<CardRepository>()
    private val colabRepository = mock<ColabRepository>()
    private val usecase = IGetDescColab(
        cardRepository = cardRepository,
        colabRepository = colabRepository
    )

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
                "IGetDescColab -> ICardRepository.getRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in ColabRepository getNameByReg - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegColab(1)
            ).thenReturn(
                Result.success(123456L)
            )
            whenever(
                colabRepository.getNameByReg(123456L)
            ).thenReturn(
                resultFailure(
                    "IColabRepository.getNameByReg",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDescColab -> IColabRepository.getNameByReg"
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
                Result.success(123456L)
            )
            whenever(
                colabRepository.getNameByReg(123456L)
            ).thenReturn(
                Result.success("ANDERSON DA SILVA DELGADO")
            )
            val result = usecase(
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
                "123456 - ANDERSON DA SILVA DELGADO"
            )
        }

    //////////////////////////////////////////////////////////////

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
                "IGetDescColab -> ICardRepository.getRegPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in ColabRepository getNameByReg - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getRegPassengerColab(1, 2)
            ).thenReturn(
                Result.success(456789L)
            )
            whenever(
                colabRepository.getNameByReg(456789L)
            ).thenReturn(
                resultFailure(
                    "IColabRepository.getNameByReg",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
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
                "IGetDescColab -> IColabRepository.getNameByReg"
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
                Result.success(456789L)
            )
            whenever(
                colabRepository.getNameByReg(456789L)
            ).thenReturn(
                Result.success("JOAO DA SILVA")
            )
            val result = usecase(
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
                "456789 - JOAO DA SILVA"
            )
        }

}
