package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetCountBreathalyzerTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetCountBreathalyzer(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getCountBreathalyzer (INSERT)`() =
        runTest {
            whenever(cardRepository.getCountBreathalyzer()).thenReturn(resultFailure("CardRepository.getCountBreathalyzer", Exception()))
            val result = usecase(Option.INSERT, 0)
            assertEquals(true, result.isFailure)
            assertEquals("IGetCountBreathalyzer -> CardRepository.getCountBreathalyzer", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return correct if function execute successfully (EDIT)`() =
        runTest {
            whenever(cardRepository.getCountBreathalyzer(1)).thenReturn(Result.success(0.15))
            val result = usecase(Option.EDIT, 1)
            assertEquals(true, result.isSuccess)
            assertEquals(0.15, result.getOrNull())
        }

}
