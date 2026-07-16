package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IHasDataLocalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IHasDataLocal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdDataLocal`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdDataLocal",
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
                "IHasDataLocal -> ICardRepository.listIdDataLocal"
            )
        }

    @Test
    fun `Check return true if CardRepository listIdDataLocal is not empty`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
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
    fun `Check return false if CardRepository listIdDataLocal is empty`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
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
