package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IHasNatureTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IHasNature(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdNature`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdNature",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IHasNature -> ICardRepository.listIdNature"
            )
        }

    @Test
    fun `Check return true if CardRepository listIdNature is not empty`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun `Check return false if CardRepository listIdNature is empty`() =
        runTest {
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

}
