package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetDataInitialBreathalyzerTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetDataInitialBreathalyzer(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setDataInitialBreathalyzer (INSERT)`() =
        runTest {
            whenever(cardRepository.setDataInitialBreathalyzer(true, false)).thenReturn(resultFailure("CardRepository.setDataInitialBreathalyzer", Exception()))
            val result = usecase(true, false, Option.INSERT, 0)
            assertEquals(true, result.isFailure)
            assertEquals("ISetDataInitialBreathalyzer -> CardRepository.setDataInitialBreathalyzer", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return correct if function execute successfully (EDIT)`() =
        runTest {
            whenever(cardRepository.updateDataInitialBreathalyzer(true, true, 1)).thenReturn(Result.success(Unit))
            val result = usecase(true, true, Option.EDIT, 1)
            assertEquals(true, result.isSuccess)
        }

}
