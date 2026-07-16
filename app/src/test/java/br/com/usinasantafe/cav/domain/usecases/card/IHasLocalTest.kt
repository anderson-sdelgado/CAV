package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IHasLocalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IHasLocal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository hasLocal`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.hasLocal",
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
                "IHasLocal -> ICardRepository.hasLocal"
            )
        }

    @Test
    fun `Check return true if CardRepository hasLocal is true`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
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
    fun `Check return false if CardRepository hasLocal is false`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(false)
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
