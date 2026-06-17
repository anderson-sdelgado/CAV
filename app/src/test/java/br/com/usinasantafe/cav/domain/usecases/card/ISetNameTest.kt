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

class ISetNameTest {

	private val cardRepository = mock<CardRepository>()
	private val usecase = ISetName(
		cardRepository = cardRepository
	)

	@Test
	fun `Check return failure if have error in CardRepository setName`() =
		runTest {
			whenever(
				cardRepository.setName("NAME")
			).thenReturn(
				resultFailure(
					"ICardRepository.setName",
					"-",
					Exception()
				)
			)
			val result = usecase(
				name = "NAME",
				option = Option.INSERT,
				flowNote = FlowNote.COLAB,
				idMain = 0,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
                .setName("NAME")
			verify(cardRepository, never())
                .updateNameDriver("NAME", 0)
			verify(cardRepository, never())
                .updateNameInvolved("NAME", 0)
			verify(cardRepository, never())
                .updateNameWitness("NAME", 0)
			verify(cardRepository, never())
                .updateNamePassengerInvolved("NAME", 0, 0)
			assertEquals(
                result.isFailure,
                true
            )
			assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetName -> ICardRepository.setName"
            )
			assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
		}

	@Test
	fun `Check return correct if CardRepository setName execute successfully`() =
		runTest {
			whenever(
				cardRepository.setName("NAME")
			).thenReturn(
				Result.success(Unit)
			)
			val result = usecase(
				name = "NAME",
				option = Option.INSERT,
				flowNote = FlowNote.COLAB,
				idMain = 0,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
                .setName("NAME")
			verify(cardRepository, never())
                .updateNameDriver("NAME", 0)
			verify(cardRepository, never())
                .updateNameInvolved("NAME", 0)
			verify(cardRepository, never())
                .updateNameWitness("NAME", 0)
			verify(cardRepository, never())
                .updateNamePassengerInvolved("NAME", 0, 0)
			assertEquals(
                result.isSuccess,
                true
            )
		}

	@Test
	fun `Check return failure if have error in CardRepository updateNameDriver`() =
		runTest {
			whenever(
				cardRepository.updateNameDriver("NAME", 1)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateNameDriver",
					"-",
					Exception()
				)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, never())
                .setName("NAME")
			verify(cardRepository, atLeastOnce())
                .updateNameDriver("NAME", 1)
			verify(cardRepository, never())
                .updateNameInvolved("NAME", 1)
			verify(cardRepository, never())
                .updateNameWitness("NAME", 1)
			verify(cardRepository, never())
                .updateNamePassengerInvolved("NAME", 1, 0)
			assertEquals(
                result.isFailure,
                true
            )
			assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetName -> ICardRepository.updateNameDriver"
            )
			assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
		}

	@Test
	fun `Check return correct if CardRepository updateNameDriver execute successfully`() =
		runTest {
			whenever(
				cardRepository.updateNameDriver("NAME", 1)
			).thenReturn(
				Result.success(Unit)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.DRIVER,
				idMain = 1,
				idSecondary = 0
			)
			verify(cardRepository, never())
                .setName("NAME")
			verify(cardRepository, atLeastOnce())
                .updateNameDriver("NAME", 1)
			assertEquals(
                result.isSuccess,
                true
            )
		}

	@Test
	fun `Check return failure if have error in CardRepository updateNameInvolved`() =
		runTest {
			whenever(
				cardRepository.updateNameInvolved("NAME", 2)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateNameInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.INVOLVED,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, never())
                .setName("NAME")
			verify(cardRepository, never())
                .updateNameDriver("NAME", 2)
			verify(cardRepository, atLeastOnce())
                .updateNameInvolved("NAME", 2)
			verify(cardRepository, never())
                .updateNameWitness("NAME", 2)
			verify(cardRepository, never())
                .updateNamePassengerInvolved("NAME", 2, 0)
			assertEquals(
                result.isFailure,
                true
            )
			assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetName -> ICardRepository.updateNameInvolved"
            )
			assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
		}

	@Test
	fun `Check return correct if CardRepository updateNameInvolved execute successfully`() =
		runTest {
			whenever(
				cardRepository.updateNameInvolved("NAME", 2)
			).thenReturn(
				Result.success(Unit)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.INVOLVED,
				idMain = 2,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
                .updateNameInvolved("NAME", 2)
			assertEquals(
                result.isSuccess,
                true
            )
		}

	@Test
	fun `Check return failure if have error in CardRepository updateNameWitness`() =
		runTest {
			whenever(
				cardRepository.updateNameWitness("NAME", 3)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateNameWitness",
					"-",
					Exception()
				)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.WITNESS,
				idMain = 3,
				idSecondary = 0
			)
			verify(cardRepository, never())
                .setName("NAME")
			verify(cardRepository, never())
                .updateNameDriver("NAME", 3)
			verify(cardRepository, never())
                .updateNameInvolved("NAME", 3)
			verify(cardRepository, atLeastOnce())
                .updateNameWitness("NAME", 3)
			verify(cardRepository, never())
                .updateNamePassengerInvolved("NAME", 3, 0)
			assertEquals(
                result.isFailure,
                true
            )
			assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetName -> ICardRepository.updateNameWitness"
            )
			assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
		}

	@Test
	fun `Check return correct if CardRepository updateNameWitness execute successfully`() =
		runTest {
			whenever(
				cardRepository.updateNameWitness("NAME", 3)
			).thenReturn(
				Result.success(Unit)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.WITNESS,
				idMain = 3,
				idSecondary = 0
			)
			verify(cardRepository, atLeastOnce())
                .updateNameWitness("NAME", 3)
			assertEquals(
                result.isSuccess,
                true
            )
		}

	@Test
	fun `Check return failure if have error in CardRepository updateNamePassengerInvolved`() =
		runTest {
			whenever(
				cardRepository.updateNamePassengerInvolved("NAME", 4, 5)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateNamePassengerInvolved",
					"-",
					Exception()
				)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.PASSENGER_INVOLVED,
				idMain = 4,
				idSecondary = 5
			)
			verify(cardRepository, never())
                .setName("NAME")
			verify(cardRepository, never())
                .updateNameDriver("NAME", 4)
			verify(cardRepository, never())
                .updateNameInvolved("NAME", 4)
			verify(cardRepository, never())
                .updateNameWitness("NAME", 4)
			verify(cardRepository, atLeastOnce())
                .updateNamePassengerInvolved("NAME", 4, 5)
			assertEquals(
                result.isFailure,
                true)
			assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetName -> ICardRepository.updateNamePassengerInvolved"
            )
			assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
		}

	@Test
	fun `Check return correct if CardRepository updateNamePassengerInvolved execute successfully`() =
		runTest {
			whenever(
				cardRepository.updateNamePassengerInvolved("NAME", 4, 5)
			).thenReturn(
				Result.success(Unit)
			)
			val result = usecase(
				name = "NAME",
				option = Option.EDIT,
				flowNote = FlowNote.PASSENGER_INVOLVED,
				idMain = 4,
				idSecondary = 5
			)
			verify(cardRepository, atLeastOnce())
                .updateNamePassengerInvolved("NAME", 4, 5)
			assertEquals(
                result.isSuccess,
                true
            )
		}

}