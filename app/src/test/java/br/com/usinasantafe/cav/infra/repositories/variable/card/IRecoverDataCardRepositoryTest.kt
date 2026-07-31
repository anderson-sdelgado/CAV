package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IRecoverDataCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val colabSharedPreferencesDatasource = mock<ColabSharedPreferencesDatasource>()
    private val equipSharedPreferencesDatasource = mock<EquipSharedPreferencesDatasource>()
    private val involvedSharedPreferencesDatasource = mock<InvolvedSharedPreferencesDatasource>()
    private val vehicleSharedPreferencesDatasource = mock<VehicleSharedPreferencesDatasource>()

    private val repository = IRecoverDataCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        colabSharedPreferencesDatasource = colabSharedPreferencesDatasource,
        equipSharedPreferencesDatasource = equipSharedPreferencesDatasource,
        involvedSharedPreferencesDatasource = involvedSharedPreferencesDatasource,
        vehicleSharedPreferencesDatasource = vehicleSharedPreferencesDatasource
    )

    @Test
    fun `listInvolvedColab - Check return failure if have error in CardSharedPreferencesDatasource`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listInvolvedColab()).thenReturn(resultFailure("CardSharedPreferencesDatasource.listInvolvedColab", Exception()))
            val result = repository.listInvolvedColab()
            assertEquals(true, result.isFailure)
            assertEquals("IRecoverDataCardRepository.listInvolvedColab -> CardSharedPreferencesDatasource.listInvolvedColab", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `listInvolvedColab - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(ColabCard(id = 1, reg = 123L))
            whenever(cardSharedPreferencesDatasource.listInvolvedColab()).thenReturn(Result.success(list))
            val result = repository.listInvolvedColab()
            assertEquals(true, result.isSuccess)
            assertEquals(list, result.getOrNull())
        }

    @Test
    fun `listWitnessColab - Check return failure if have error in CardSharedPreferencesDatasource`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listWitnessColab()).thenReturn(resultFailure("CardSharedPreferencesDatasource.listWitnessColab", Exception()))
            val result = repository.listWitnessColab()
            assertEquals(true, result.isFailure)
            assertEquals("IRecoverDataCardRepository.listWitnessColab -> CardSharedPreferencesDatasource.listWitnessColab", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `listWitnessColab - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(ColabCard(id = 1, reg = 123L))
            whenever(cardSharedPreferencesDatasource.listWitnessColab()).thenReturn(Result.success(list))
            val result = repository.listWitnessColab()
            assertEquals(true, result.isSuccess)
            assertEquals(list, result.getOrNull())
        }

}
