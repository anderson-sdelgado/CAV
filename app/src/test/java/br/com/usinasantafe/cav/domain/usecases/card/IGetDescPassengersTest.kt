package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
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
    fun `Check return failure if have error in CardRepository listPassengerColab - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.listPassengerColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listPassengerColab",
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
                "IGetDescPassengers -> ICardRepository.listPassengerColab"
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
                cardRepository.listPassengerColab(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        ColabCard(
                            id = 1,
                            reg = 123456L,
                        ),
                        ColabCard(
                            id = 2,
                            reg = 456789L,
                        )
                    )
                )
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
                cardRepository.listPassengerColab(1)
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
                cardRepository.listPassengerColab(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        ColabCard(
                            id = 1,
                            reg = 123456L,
                        ),
                        ColabCard(
                            id = 2,
                            reg = 456789L,
                        )
                    )
                )
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
                cardRepository.listPassengerExternal(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_EXTERNAL,
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
                cardRepository.listPassengerExternal(1)
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_EXTERNAL,
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
                cardRepository.listPassengerExternal(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        PeopleExternal(id = 1, document = "123", name = "INVOLVED 1"),
                        PeopleExternal(id = 2, document = null, name = "INVOLVED 2")
                    )
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_EXTERNAL,
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
