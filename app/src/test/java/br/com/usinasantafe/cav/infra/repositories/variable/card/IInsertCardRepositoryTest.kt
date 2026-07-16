package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IInsertCardRepositoryTest {

    private val cardSharedPreferencesDatasource = Mockito.mock<CardSharedPreferencesDatasource>()
    private val equipSharedPreferencesDatasource = Mockito.mock<EquipSharedPreferencesDatasource>()
    private val colabSharedPreferencesDatasource = Mockito.mock<ColabSharedPreferencesDatasource>()
    private val vehicleSharedPreferencesDatasource =
        Mockito.mock<VehicleSharedPreferencesDatasource>()
    private val involvedSharedPreferencesDatasource =
        Mockito.mock<InvolvedSharedPreferencesDatasource>()
    private val repository = IInsertCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        equipSharedPreferencesDatasource = equipSharedPreferencesDatasource,
        colabSharedPreferencesDatasource = colabSharedPreferencesDatasource,
        vehicleSharedPreferencesDatasource = vehicleSharedPreferencesDatasource,
        involvedSharedPreferencesDatasource = involvedSharedPreferencesDatasource
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
                    EquipCardSharedPreferencesModel()
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
                    EquipCardSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    ColabCardSharedPreferencesModel()
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
                    EquipCardSharedPreferencesModel()
                )
            )
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    ColabCardSharedPreferencesModel()
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
            val equipCard = EquipCardSharedPreferencesModel()
            val colabCard = ColabCardSharedPreferencesModel()
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
                cardSharedPreferencesDatasource.addVehicleOwn(
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
            val equipCard = EquipCardSharedPreferencesModel()
            val colabCard = ColabCardSharedPreferencesModel()
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
                cardSharedPreferencesDatasource.addVehicleOwn(
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

    @Test
    fun `setDetailVehicle - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setDetailVehicle("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setDetailEquipSec - Check return failure if have error in EquipSharedPreferencesDatasource setDetail`() =
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
            val result = repository.setDetailEquipSec("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailEquipSec -> IEquipSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailEquipSec - Check return failure if have error in EquipSharedPreferencesDatasource get`() =
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
            val result = repository.setDetailEquipSec("test", 1)
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailEquipSec -> IEquipSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailEquipSec - Check return failure if have error in EquipSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    EquipCardSharedPreferencesModel()
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
            val result = repository.setDetailEquipSec("test", 1)
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailEquipSec -> IEquipSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailEquipSec - Check return failure if have error in CardSharedPreferencesDatasource addEquipSec`() =
        runTest {
            val equipCard = EquipCardSharedPreferencesModel()
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    equipCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addEquipSec(
                    equipCard,
                    1
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addEquipSec",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailEquipSec("test", 1)
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailEquipSec -> ICardSharedPreferencesDatasource.addEquipSec"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailEquipSec - Check return correct if function execute successfully`() =
        runTest {
            val equipCard = EquipCardSharedPreferencesModel()
            whenever(
                equipSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    equipCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addEquipSec(
                    equipCard,
                    1
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailEquipSec("test", 1)
            verify(equipSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(equipSharedPreferencesDatasource, atLeastOnce()).clean()
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
    fun `setDetailDriver - Check return failure if have error in InvolvedSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> IInvolvedSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return failure if have error in VehicleSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IVehicleSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> IVehicleSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return failure if have error in InvolvedSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    VehicleSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> IInvolvedSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return failure if have error in VehicleSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    VehicleSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    InvolvedSharedPreferencesModel()
                )
            )
            whenever(
                vehicleSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IVehicleSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> IVehicleSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return failure if have error in InvolvedSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    VehicleSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    InvolvedSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> IInvolvedSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return failure if have error in CardSharedPreferencesDatasource addVehicleInvolved`() =
        runTest {
            val vehicle = VehicleSharedPreferencesModel()
            val driver = InvolvedSharedPreferencesModel()
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    vehicle
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    driver
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addVehicleInvolved(
                    VehicleInvolvedSharedPreferencesModel(vehicle = vehicle, driver = driver)
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addVehicleInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).clean()
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailDriver -> ICardSharedPreferencesDatasource.addVehicleInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailDriver - Check return correct if function execute successfully`() =
        runTest {
            val vehicle = VehicleSharedPreferencesModel()
            val driver = InvolvedSharedPreferencesModel()
            whenever(
                vehicleSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    vehicle
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    driver
                )
            )
            val result = repository.setDetailDriver("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).clean()
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
            verify(cardSharedPreferencesDatasource, atLeastOnce()).addVehicleInvolved(
                VehicleInvolvedSharedPreferencesModel(vehicle = vehicle, driver = driver)
            )
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setDetailPassengerColab - Check return failure if have error in ColabSharedPreferencesDatasource setDetail`() =
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
            val result = repository.setDetailPassengerColab("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerColab -> IColabSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerColab - Check return failure if have error in ColabSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerColab("test", 1)
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerColab -> IColabSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource addPassengerColab`() =
        runTest {
            val colabCard = ColabCardSharedPreferencesModel()
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    colabCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addPassengerColab(
                    colabCard,
                    1
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerColab("test", 1)
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerColab -> ICardSharedPreferencesDatasource.addPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val colabCard = ColabCardSharedPreferencesModel()
            whenever(
                colabSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    colabCard
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addPassengerColab(
                    colabCard,
                    1
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailPassengerColab("test", 1)
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
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
    fun `setDetailInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailInvolved("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailInvolved -> IInvolvedSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailInvolved("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailInvolved -> IInvolvedSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    InvolvedSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailInvolved("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailInvolved -> IInvolvedSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailInvolved - Check return failure if have error in CardSharedPreferencesDatasource addInvolved`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addInvolved(
                    involved
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailInvolved("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailInvolved -> ICardSharedPreferencesDatasource.addInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailInvolved - Check return correct if function execute successfully`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addInvolved(
                    involved
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailInvolved("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
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
    fun `setDetailWitness - Check return failure if have error in InvolvedSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailWitness("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailWitness -> IInvolvedSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailWitness - Check return failure if have error in InvolvedSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailWitness("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailWitness -> IInvolvedSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailWitness - Check return failure if have error in InvolvedSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    InvolvedSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailWitness("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailWitness -> IInvolvedSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailWitness - Check return failure if have error in CardSharedPreferencesDatasource addWitness`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addWitness(
                    involved
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailWitness("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailWitness -> ICardSharedPreferencesDatasource.addWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailWitness - Check return correct if function execute successfully`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addWitness(
                    involved
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailWitness("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
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
    fun `setDetailPassengerInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource setDetail`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setDetail("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerInvolved("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerInvolved -> IInvolvedSharedPreferencesDatasource.setDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource get`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.get",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerInvolved("test", 1)
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerInvolved -> IInvolvedSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    InvolvedSharedPreferencesModel()
                )
            )
            whenever(
                involvedSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerInvolved("test", 1)
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerInvolved -> IInvolvedSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource addPassengerInvolved`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addPassengerInvolved(
                    involved,
                    1
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.addPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDetailPassengerInvolved("test", 1)
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDetailPassengerInvolved -> ICardSharedPreferencesDatasource.addPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDetailPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val involved = InvolvedSharedPreferencesModel()
            whenever(
                involvedSharedPreferencesDatasource.get()
            ).thenReturn(
                Result.success(
                    involved
                )
            )
            whenever(
                cardSharedPreferencesDatasource.addPassengerInvolved(
                    involved,
                    1
                )
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.setDetailPassengerInvolved("test", 1)
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDetail("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).clean()
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
    fun `setRegColab - Check return failure if have error in ColabSharedPreferencesDatasource setRegColab`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.setRegColab(123456)
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.setRegColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.setRegColab(123456)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setRegColab -> IColabSharedPreferencesDatasource.setRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setRegColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setRegColab(123456)
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setRegColab(123456)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setStateColab - Check return failure if have error in ColabSharedPreferencesDatasource setState`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.setState(State.UNHARMED)
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.setState",
                    "-",
                    Exception()
                )
            )
            val result = repository.setStateColab(State.UNHARMED)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setStateColab -> IColabSharedPreferencesDatasource.setState"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setStateColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setStateColab(State.UNHARMED)
            verify(colabSharedPreferencesDatasource, atLeastOnce()).setState(State.UNHARMED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setBrand - Check return failure if have error in VehicleSharedPreferencesDatasource setBrand`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.setBrand("test")
            ).thenReturn(
                resultFailure(
                    "IVehicleSharedPreferencesDatasource.setBrand",
                    "-",
                    Exception()
                )
            )
            val result = repository.setBrand("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setBrand -> IVehicleSharedPreferencesDatasource.setBrand"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setBrand - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setBrand("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).setBrand("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setPlate - Check return failure if have error in VehicleSharedPreferencesDatasource setPlate`() =
        runTest {
            whenever(
                vehicleSharedPreferencesDatasource.setPlate("test")
            ).thenReturn(
                resultFailure(
                    "IVehicleSharedPreferencesDatasource.setPlate",
                    "-",
                    Exception()
                )
            )
            val result = repository.setPlate("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setPlate -> IVehicleSharedPreferencesDatasource.setPlate"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setPlate - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setPlate("test")
            verify(vehicleSharedPreferencesDatasource, atLeastOnce()).setPlate("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setDocument - Check return failure if have error in InvolvedSharedPreferencesDatasource setDocument`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setDocument("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setDocument",
                    "-",
                    Exception()
                )
            )
            val result = repository.setDocument("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setDocument -> IInvolvedSharedPreferencesDatasource.setDocument"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setDocument - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setDocument("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setDocument("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setStateInvolved - Check return failure if have error in InvolvedSharedPreferencesDatasource setState`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setState(State.UNHARMED)
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setState",
                    "-",
                    Exception()
                )
            )
            val result = repository.setStateInvolved(State.UNHARMED)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setStateInvolved -> IInvolvedSharedPreferencesDatasource.setState"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setStateInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setStateInvolved(State.UNHARMED)
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setState(State.UNHARMED)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setName - Check return failure if have error in InvolvedSharedPreferencesDatasource setName`() =
        runTest {
            whenever(
                involvedSharedPreferencesDatasource.setName("test")
            ).thenReturn(
                resultFailure(
                    "IInvolvedSharedPreferencesDatasource.setName",
                    "-",
                    Exception()
                )
            )
            val result = repository.setName("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IInsertCardRepository.setName -> IInvolvedSharedPreferencesDatasource.setName"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setName - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setName("test")
            verify(involvedSharedPreferencesDatasource, atLeastOnce()).setName("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}