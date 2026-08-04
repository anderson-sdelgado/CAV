package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
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

    @Test
    fun `getRealizedBreathalyzer - Check return failure if have error in CardSharedPreferencesDatasource`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRealizedBreathalyzer(1)).thenReturn(resultFailure("CardSharedPreferencesDatasource.getRealizedBreathalyzer", Exception()))
            val result = repository.getRealizedBreathalyzer(1)
            assertEquals(true, result.isFailure)
            assertEquals("IRecoverDataCardRepository.getRealizedBreathalyzer -> CardSharedPreferencesDatasource.getRealizedBreathalyzer", result.exceptionOrNull()!!.message)
        }

    @Test
    fun `getRealizedBreathalyzer - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRealizedBreathalyzer(1)).thenReturn(Result.success(true))
            val result = repository.getRealizedBreathalyzer(1)
            assertEquals(true, result.isSuccess)
            assertEquals(true, result.getOrNull())
        }

    @Test
    fun `getResultBreathalyzer - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getResultBreathalyzer(1)).thenReturn(Result.success(false))
            val result = repository.getResultBreathalyzer(1)
            assertEquals(true, result.isSuccess)
            assertEquals(false, result.getOrNull())
        }

    @Test
    fun `getBreathalyzer - Check return correct if function execute successfully`() =
        runTest {
            val triple = Triple(true, false, 0.5)
            whenever(cardSharedPreferencesDatasource.getBreathalyzer(1)).thenReturn(Result.success(triple))
            val result = repository.getBreathalyzer(1)
            assertEquals(true, result.isSuccess)
            assertEquals(triple, result.getOrNull())
        }

    @Test
    fun `getDetailColab - Check return correct value`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailColab(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailColab(1)
            assertEquals(true, result.isSuccess)
            assertEquals("test", result.getOrNull())
        }

    @Test
    fun `getStateColab - Check return correct value`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateColab(1)).thenReturn(Result.success(State.DEAD))
            val result = repository.getStateColab(1)
            assertEquals(true, result.isSuccess)
            assertEquals(State.DEAD, result.getOrNull())
        }

}
