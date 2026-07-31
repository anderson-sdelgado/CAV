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

class ISetAddressTest {

	private val cardRepository = mock<CardRepository>()
	private val usecase = ISetAddress(
		cardRepository = cardRepository
	)

	@Test
	fun `Check return failure if have error in CardRepository setAddressDriver`() =
		runTest {
			whenever(
				cardRepository.updateAddressDriver("ADDRESS", 1)
			).thenReturn(
				resultFailure(
					"ICardRepository.setAddressDriver",
					"-",
					Exception()
				)
			)
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
				.updateAddressDriver("ADDRESS", 1)
			verify(cardRepository, never())
				.updateAddressInvolved("ADDRESS", 1)
			verify(cardRepository, never())
				.updateAddressPassengerInvolved("ADDRESS", 1, 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetAddress -> ICardRepository.setAddressDriver"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setAddressDriver execute successfully`() =
		runTest {
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
				.updateAddressDriver("ADDRESS", 1)
			verify(cardRepository, never())
				.updateAddressInvolved("ADDRESS", 1)
			verify(cardRepository, never())
				.updateAddressPassengerInvolved("ADDRESS", 1, 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository setAddressInvolved`() =
		runTest {
			whenever(
				cardRepository.updateAddressInvolved("ADDRESS", 2)
			).thenReturn(
				resultFailure(
					"ICardRepository.setAddressInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.INVOLVED_EXTERNAL,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.updateAddressDriver("ADDRESS", 2)
			verify(cardRepository, atLeastOnce())
				.updateAddressInvolved("ADDRESS", 2)
			verify(cardRepository, never())
				.updateAddressPassengerInvolved("ADDRESS", 2, 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetAddress -> ICardRepository.setAddressInvolved"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setAddressInvolved execute successfully`() =
		runTest {
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.INVOLVED_EXTERNAL,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.updateAddressDriver("ADDRESS", 2)
			verify(cardRepository, atLeastOnce())
				.updateAddressInvolved("ADDRESS", 2)
			verify(cardRepository, never())
				.updateAddressPassengerInvolved("ADDRESS", 2, 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository setAddressPassengerInvolved`() =
		runTest {
			whenever(
				cardRepository.updateAddressPassengerInvolved("ADDRESS", 3, 4)
			).thenReturn(
				resultFailure(
					"ICardRepository.setAddressPassengerInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.PASSENGER_EXTERNAL,
				idMain = 3,
				idSecondary = 4
			)
			verify(cardRepository, never())
				.updateAddressDriver("ADDRESS", 3)
			verify(cardRepository, never())
				.updateAddressInvolved("ADDRESS", 3)
			verify(cardRepository, atLeastOnce())
				.updateAddressPassengerInvolved("ADDRESS", 3, 4)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetAddress -> ICardRepository.setAddressPassengerInvolved"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setAddressPassengerInvolved execute successfully`() =
		runTest {
			val result = usecase(
				address = "ADDRESS",
				flowNote = FlowNote.PASSENGER_EXTERNAL,
				idMain = 3,
				idSecondary = 4
			)
			verify(cardRepository, never())
				.updateAddressDriver("ADDRESS", 3)
			verify(cardRepository, never())
				.updateAddressInvolved("ADDRESS", 3)
			verify(cardRepository, atLeastOnce())
				.updateAddressPassengerInvolved("ADDRESS", 3, 4)
			assertEquals(
				result.isSuccess,
				true
			)
		}

}