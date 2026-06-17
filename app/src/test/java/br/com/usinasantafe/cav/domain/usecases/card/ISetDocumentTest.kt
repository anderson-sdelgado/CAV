package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetDocumentTest {

	private val cardRepository = mock<CardRepository>()
	private val usecase = ISetDocument(
		cardRepository = cardRepository
	)

	@Test
	fun `Check return failure if have error in CardRepository setDocument`() =
		runTest {
			whenever(
				cardRepository.setDocument("12345678900")
			).thenReturn(
				resultFailure(
					"ICardRepository.setDocument",
					"-",
					Exception()
				)
			)
			val result = usecase(
				cpf = "12345678900",
				option = Option.INSERT,
				flowNote = FlowNote.DRIVER,
				idMain = 0,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 0)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 0)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 0, 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetDocument -> ICardRepository.setDocument"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setDocument execute successfully`() =
		runTest {
			val result = usecase(
				cpf = "12345678900",
				option = Option.INSERT,
				flowNote = FlowNote.DRIVER,
				idMain = 0,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 0)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 0)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 0, 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository updateDocumentDriver`() =
		runTest {
			whenever(
				cardRepository.updateDocumentDriver("12345678900", 1)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateDocumentDriver",
					"-",
					Exception()
				)
			)
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, atLeastOnce())
				.updateDocumentDriver("12345678900", 1)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 1)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 1, 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetDocument -> ICardRepository.updateDocumentDriver"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository updateDocumentDriver execute successfully`() =
		runTest {
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, atLeastOnce())
				.updateDocumentDriver("12345678900", 1)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 1)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 1, 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository updateDocumentInvolved`() =
		runTest {
			whenever(
				cardRepository.updateDocumentInvolved("12345678900", 2)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateDocumentInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.INVOLVED,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 2)
			verify(cardRepository, atLeastOnce())
				.updateDocumentInvolved("12345678900", 2)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 2, 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetDocument -> ICardRepository.updateDocumentInvolved"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository updateDocumentInvolved execute successfully`() =
		runTest {
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.INVOLVED,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 2)
			verify(cardRepository, atLeastOnce())
				.updateDocumentInvolved("12345678900", 2)
			verify(cardRepository, never())
				.updateDocumentPassengerInvolved("12345678900", 2, 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository updateDocumentPassengerInvolved`() =
		runTest {
			whenever(
				cardRepository.updateDocumentPassengerInvolved("12345678900", 3, 4)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateDocumentPassengerInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.PASSENGER_INVOLVED,
				idMain = 3,
				idSecondary = 4
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 3)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 3)
			verify(cardRepository, atLeastOnce())
				.updateDocumentPassengerInvolved("12345678900", 3, 4)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetDocument -> ICardRepository.updateDocumentPassengerInvolved"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository updateDocumentPassengerInvolved execute successfully`() =
		runTest {
			val result = usecase(
				cpf = "12345678900",
				option = Option.EDIT,
				flowNote = FlowNote.PASSENGER_INVOLVED,
				idMain = 3,
				idSecondary = 4
			)
			verify(cardRepository, never())
				.setDocument("12345678900")
			verify(cardRepository, never())
				.updateDocumentDriver("12345678900", 3)
			verify(cardRepository, never())
				.updateDocumentInvolved("12345678900", 3)
			verify(cardRepository, atLeastOnce())
				.updateDocumentPassengerInvolved("12345678900", 3, 4)
			assertEquals(
				result.isSuccess,
				true
			)
		}

}