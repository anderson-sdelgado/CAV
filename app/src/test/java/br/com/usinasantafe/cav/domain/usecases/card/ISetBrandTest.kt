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

class ISetBrandTest {

	private val cardRepository = mock<CardRepository>()
	private val usecase = ISetBrand(
		cardRepository = cardRepository
	)

	@Test
	fun `Check return failure if have error in CardRepository setBrand`() =
		runTest {
			whenever(
				cardRepository.setBrand("BRAND")
			).thenReturn(
				resultFailure(
					"ICardRepository.setBrand",
					"-",
					Exception()
				)
			)
			val result = usecase(
				text = "BRAND",
				option = Option.INSERT,
				idMain = 0
			)
			verify(cardRepository, atLeastOnce())
				.setBrand("BRAND")
			verify(cardRepository, never())
				.updateBrand("BRAND", 0)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetBrand -> ICardRepository.setBrand"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository setBrand execute successfully`() =
		runTest {
			val result = usecase(
				text = "BRAND",
				option = Option.INSERT,
				idMain = 0
			)
			verify(cardRepository, atLeastOnce())
				.setBrand("BRAND")
			verify(cardRepository, never())
				.updateBrand("BRAND", 0)
			assertEquals(
				result.isSuccess,
				true
			)
		}

	@Test
	fun `Check return failure if have error in CardRepository updateBrand`() =
		runTest {
			whenever(
				cardRepository.updateBrand("BRAND", 5)
			).thenReturn(
				resultFailure(
					"ICardRepository.updateBrand",
					"-",
					Exception()
				)
			)
			val result = usecase(
				text = "BRAND",
				option = Option.EDIT,
				idMain = 5
			)
			verify(cardRepository, never())
				.setBrand("BRAND")
			verify(cardRepository, atLeastOnce())
				.updateBrand("BRAND", 5)
			assertEquals(
				result.isFailure,
				true
			)
			assertEquals(
				result.exceptionOrNull()!!.message,
				"ISetBrand -> ICardRepository.updateBrand"
			)
			assertEquals(
				result.exceptionOrNull()!!.cause.toString(),
				"java.lang.Exception"
			)
		}

	@Test
	fun `Check return correct if CardRepository updateBrand execute successfully`() =
		runTest {
			val result = usecase(
				text = "BRAND",
				option = Option.EDIT,
				idMain = 5
			)
			verify(cardRepository, never())
				.setBrand("BRAND")
			verify(cardRepository, atLeastOnce())
				.updateBrand("BRAND", 5)
			assertEquals(
				result.isSuccess,
				true
			)
		}

}