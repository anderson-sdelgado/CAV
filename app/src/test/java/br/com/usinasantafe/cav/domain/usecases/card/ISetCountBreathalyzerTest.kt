package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetCountBreathalyzerTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetCountBreathalyzer(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setCountBreathalyzer (INSERT)`() =
        runTest {
            whenever(cardRepository.setCountBreathalyzer(0.05)).thenReturn(resultFailure("CardRepository.setCountBreathalyzer", Exception()))
            val result = usecase("0,05", Option.INSERT, 0)
            assertEquals(true, result.isFailure)
            assertEquals("ISetCountBreathalyzer -> CardRepository.setCountBreathalyzer", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return correct if function execute successfully (EDIT)`() =
        runTest {
            whenever(cardRepository.updateCountBreathalyzer(0.12, 1)).thenReturn(Result.success(Unit))
            val result = usecase("0,12", Option.EDIT, 1)
            assertEquals(true, result.isSuccess)
        }

}
