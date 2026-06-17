package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IInsertCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val equipSharedPreferencesDatasource = mock<EquipSharedPreferencesDatasource>()
    private val colabSharedPreferencesDatasource = mock<ColabSharedPreferencesDatasource>()
    private val vehicleSharedPreferencesDatasource = mock<VehicleSharedPreferencesDatasource>()
    private val repository = IInsertCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        equipSharedPreferencesDatasource = equipSharedPreferencesDatasource,
        colabSharedPreferencesDatasource = colabSharedPreferencesDatasource,
        vehicleSharedPreferencesDatasource = vehicleSharedPreferencesDatasource
    )

    @Test
    fun `setIdEquip - Check return failure if have error in EquipSharedPreferencesDatasource setIdEquip`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.setIdEquip(1)
            ).thenReturn(
                resultFailure(
                    "IEquipSharedPreferencesDatasource.setIdEquip",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdEquip(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setIdEquip -> IEquipSharedPreferencesDatasource.setIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdEquip - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdEquip(1)
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setIdEquip(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setDetailEquip - Check return failure if have error in EquipSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IEquipSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailEquip("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailEquip -> IEquipSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailEquip - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setDetailEquip("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in ColabSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> IColabSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in EquipSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IEquipSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> IEquipSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in ColabSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    EquipSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> IColabSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in EquipSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    EquipSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    ColabSharedPreferencesModel()
                )
            )
            whenever(
                equipSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IEquipSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> IEquipSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in ColabSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    EquipSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    ColabSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> IColabSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return failure if have error in CardSharedPreferencesDatasource setVehicleOwn`() =
        runTest {
            val equipCard = EquipSharedPreferencesModel()
            val colabCard = ColabSharedPreferencesModel()
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    equipCard
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    colabCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.setVehicleOwn(
                    VehicleOwnSharedPreferencesModel(equip = equipCard, colab = colabCard)
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setVehicleOwn",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).clean()
            verify(colabSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailColab -> ICardSharedPreferencesDatasource.setVehicleOwn"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailColab - Check return correct if function execute successfully`() =
        runTest {
            val equipCard = EquipSharedPreferencesModel()
            val colabCard = ColabSharedPreferencesModel()
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    equipCard
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    colabCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.setVehicleOwn(
                    VehicleOwnSharedPreferencesModel(equip = equipCard, colab = colabCard)
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailColab("test")
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).clean()
            verify(colabSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                1
            )
        }

    @Test
    fun `setDetailVehicle - Check return failure if have error in VehicleSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IVehicleSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailVehicle("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailVehicle -> IVehicleSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

}
