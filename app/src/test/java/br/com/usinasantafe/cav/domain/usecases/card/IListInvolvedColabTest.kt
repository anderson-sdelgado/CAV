package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListInvolvedColabTest {

    private val cardRepository = mock<CardRepository>()
    private val colabRepository = mock<ColabRepository>()
    private val usecase = IListInvolvedColab(
        cardRepository = cardRepository,
        colabRepository = colabRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listInvolvedColab`() =
        runTest {
            whenever(cardRepository.listInvolvedColab()).thenReturn(resultFailure("CardRepository.listInvolvedColab", Exception()))
            val result = usecase()
            assertEquals(true, result.isFailure)
            assertEquals("IListInvolvedColab -> CardRepository.listInvolvedColab", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return failure if have error in ColabRepository listColabByRegList`() =
        runTest {
            val list = listOf(ColabCard(id = 1, reg = 123L))
            whenever(cardRepository.listInvolvedColab()).thenReturn(Result.success(list))
            whenever(colabRepository.listColabByRegList(listOf(123L))).thenReturn(resultFailure("ColabRepository.listColabByRegList", Exception()))
            
            val result = usecase()
            assertEquals(true, result.isFailure)
            assertEquals("IListInvolvedColab -> ColabRepository.listColabByRegList", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `Check return success if all data recovered correctly`() =
        runTest {
            val list = listOf(
                ColabCard(id = 1, reg = 123L),
                ColabCard(id = 2, reg = 456L)
            )
            val colabList = listOf(
                Colab(reg = 123L, name = "TEST 1"),
                Colab(reg = 456L, name = "TEST 2")
            )
            whenever(cardRepository.listInvolvedColab()).thenReturn(Result.success(list))
            whenever(colabRepository.listColabByRegList(listOf(123L, 456L))).thenReturn(Result.success(colabList))

            val result = usecase()
            assertEquals(true, result.isSuccess)
            val expected = listOf(
                ItemListScreenModel(1, "123 - TEST 1"),
                ItemListScreenModel(2, "456 - TEST 2")
            )
            assertEquals(expected, result.getOrNull())
        }
}
