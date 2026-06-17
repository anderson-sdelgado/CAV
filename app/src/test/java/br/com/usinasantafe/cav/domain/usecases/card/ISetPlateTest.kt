package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
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

class ISetPlateTest {

	private val cardRepository = mock<CardRepository>()
	private val usecase = ISetPlate(
		cardRepository = cardRepository
	)

	@Test
	fun `Check return failure if have error in CardRepository setPlate`() =
		runTest {
			whenever(
				cardRepository.setPlate("PLATE")
			).thenReturn(
				resultFailure(
					"ICardRepository.setPlate",
					"-",
					Exception()
				)
			)
			val result = usecase(
				text = "PLATE",
				option = Option.INSERT,
				idMain = 0
			)
			verify(cardRepository, atLeastOnce())
				.setPlate("PLATE")
			verify(cardRepository, never())
				.updatePlate("PLATE", 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetPlate -> ICardRepository.setPlate"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setPlate execute successfully`() =
		runTest {
			val result = usecase(
				text = "PLATE",
				option = Option.INSERT,
				idMain = 0
			)
			verify(cardRepository, atLeastOnce())
				.setPlate("PLATE")
			verify(cardRepository, never())
				.updatePlate("PLATE", 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository updatePlate`() =
		runTest {
			whenever(
				cardRepository.updatePlate("PLATE", 5)
			).thenReturn(
				resultFailure(
					"ICardRepository.updatePlate",
					"-",
					Exception()
				)
			)
			val result = usecase(
				text = "PLATE",
				option = Option.EDIT,
				idMain = 5
			)
			verify(cardRepository, never())
				.setPlate("PLATE")
			verify(cardRepository, atLeastOnce())
				.updatePlate("PLATE", 5)
			assertEquals(
				result.isFailure,
				true
            )
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetPlate -> ICardRepository.updatePlate"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository updatePlate execute successfully`() =
		runTest {
			val result = usecase(
				text = "PLATE",
				option = Option.EDIT,
				idMain = 5
			)
			verify(cardRepository, never())
				.setPlate("PLATE")
			verify(cardRepository, atLeastOnce())
				.updatePlate("PLATE", 5)
			assertEquals(
				result.isSuccess,
				true
			)
		}

}
