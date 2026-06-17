package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetPhoneTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetPhone(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository updatePhoneDriver`() =
        runTest {
            whenever(
                cardRepository.updatePhoneDriver("16999999999", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updatePhoneDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce())
                .updatePhoneDriver("16999999999", 1)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 1)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 1)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 1, 0)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetPhone -> ICardRepository.updatePhoneDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if CardRepository updatePhoneDriver execute successfully`() =
        runTest {
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce())
                .updatePhoneDriver("16999999999", 1)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 1)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 1)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 1, 0)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updatePhoneInvolved`() =
        runTest {
            whenever(
                cardRepository.updatePhoneInvolved("16999999999", 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updatePhoneInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.INVOLVED,
                idMain = 2,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 2)
            verify(cardRepository, atLeastOnce())
                .updatePhoneInvolved("16999999999", 2)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 2)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 2, 0)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetPhone -> ICardRepository.updatePhoneInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if CardRepository updatePhoneInvolved execute successfully`() =
        runTest {
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.INVOLVED,
                idMain = 2,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 2)
            verify(cardRepository, atLeastOnce())
                .updatePhoneInvolved("16999999999", 2)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 2)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 2, 0)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updatePhoneWitness`() =
        runTest {
            whenever(
                cardRepository.updatePhoneWitness("16999999999", 3)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updatePhoneWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.WITNESS,
                idMain = 3,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 3)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 3)
            verify(cardRepository, atLeastOnce())
                .updatePhoneWitness("16999999999", 3)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 3, 0)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetPhone -> ICardRepository.updatePhoneWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if CardRepository updatePhoneWitness execute successfully`() =
        runTest {
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.WITNESS,
                idMain = 3,
                idSecondary = 0
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 3)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 3)
            verify(cardRepository, atLeastOnce())
                .updatePhoneWitness("16999999999", 3)
            verify(cardRepository, never())
                .updatePhonePassengerInvolved("16999999999", 3, 0)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository updatePhonePassengerInvolved`() =
        runTest {
            whenever(
                cardRepository.updatePhonePassengerInvolved("16999999999", 4, 5)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updatePhonePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 4,
                idSecondary = 5
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 4)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 4)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 4)
            verify(cardRepository, atLeastOnce())
                .updatePhonePassengerInvolved("16999999999", 4, 5)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetPhone -> ICardRepository.updatePhonePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if CardRepository updatePhonePassengerInvolved execute successfully`() =
        runTest {
            val result = usecase(
                phone = "16999999999",
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 4,
                idSecondary = 5
            )
            verify(cardRepository, never())
                .updatePhoneDriver("16999999999", 4)
            verify(cardRepository, never())
                .updatePhoneInvolved("16999999999", 4)
            verify(cardRepository, never())
                .updatePhoneWitness("16999999999", 4)
            verify(cardRepository, atLeastOnce())
                .updatePhonePassengerInvolved("16999999999", 4, 5)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
