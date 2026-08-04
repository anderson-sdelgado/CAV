package br.com.usinasantafe.cav.domain.usecases.card

import android.content.Context
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescBreathalyzerTest {

    private val context = mock<Context>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetDescBreathalyzer(
        context = context,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getBreathalyzer`() = runTest {
        whenever(cardRepository.getBreathalyzer(1)).thenReturn(resultFailure("CardRepository.getBreathalyzer", Exception()))
        val result = usecase(1)
        assertEquals(true, result.isFailure)
        assertEquals("IGetDescBreathalyzer -> CardRepository.getBreathalyzer", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `Check return correct string if realized is true and result is positive`() = runTest {
        whenever(cardRepository.getBreathalyzer(1)).thenReturn(Result.success(Triple(true, true, 0.12)))
        whenever(context.getString(R.string.text_realized_yes)).thenReturn("REALIZADO")
        whenever(context.getString(R.string.text_pattern_positive)).thenReturn("POSITIVO")
        
        val result = usecase(1)
        assertEquals(true, result.isSuccess)
        assertEquals("REALIZADO - POSITIVO - 0,12", result.getOrNull())
    }

    @Test
    fun `Check return correct string if realized is false`() = runTest {
        whenever(cardRepository.getBreathalyzer(1)).thenReturn(Result.success(Triple(null, false, null)))
        whenever(context.getString(R.string.text_realized_no)).thenReturn("NÃO REALIZADO")
        
        val result = usecase(1)
        assertEquals(true, result.isSuccess)
        assertEquals("NÃO REALIZADO", result.getOrNull())
    }

}
