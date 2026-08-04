package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetRealizedBreathalyzerTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetRealizedBreathalyzer(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getRealizedBreathalyzer (INSERT)`() =
        runTest {
            whenever(cardRepository.getRealizedBreathalyzer()).thenReturn(resultFailure("CardRepository.getRealizedBreathalyzer", Exception()))
            val result = usecase(Option.INSERT, 0)
            assertEquals(true, result.isFailure)
            assertEquals("IGetRealizedBreathalyzer -> CardRepository.getRealizedBreathalyzer", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return correct if function execute successfully (EDIT)`() =
        runTest {
            whenever(cardRepository.getRealizedBreathalyzer(1)).thenReturn(Result.success(true))
            val result = usecase(Option.EDIT, 1)
            assertEquals(true, result.isSuccess)
            assertEquals(true, result.getOrNull())
        }

}
