package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@HiltAndroidTest
class ISetDetailTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetDetail

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource

    @Inject
    lateinit var colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Inject
    lateinit var vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    // region INSERT FLOWS

    @Test
    fun check_insert_detail_equip() = runTest {
        equipSharedPreferencesDatasource.clean()
        val result = usecase("DET EQUIP", Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(equipSharedPreferencesDatasource.get().getOrNull()?.detail, "DET EQUIP")
    }

    @Test
    fun check_insert_detail_equip_sec() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1))
        ))
        // setDetailEquipSec consolidates data from equipSharedPreferencesDatasource
        equipSharedPreferencesDatasource.setIdEquip(100)
        
        val result = usecase("DET EQUIP SEC", Option.INSERT, FlowNote.EQUIP_SEC, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1) // Returns the new ID of the equipSec
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.vehicleOwnList[0].equipSecList[0].detail, "DET EQUIP SEC")
        assertEquals(model.vehicleOwnList[0].equipSecList[0].idEquip, 100)
        
        // Check if temporary datasource was cleaned
        assertNull(equipSharedPreferencesDatasource.get().getOrNull()?.idEquip)
    }

    @Test
    fun check_insert_detail_colab() = runTest {
        // setDetailColab consolidates data from equipSharedPreferencesDatasource and colabSharedPreferencesDatasource
        equipSharedPreferencesDatasource.setIdEquip(100)
        colabSharedPreferencesDatasource.setRegColab(12345L)
        
        val result = usecase("DET COLAB", Option.INSERT, FlowNote.COLAB, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1) // Returns the new ID of the vehicleOwn
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.vehicleOwnList[0].colab.detail, "DET COLAB")
        assertEquals(model.vehicleOwnList[0].colab.reg, 12345L)
        assertEquals(model.vehicleOwnList[0].equip.idEquip, 100)
        
        // Check if temporary datasources were cleaned
        assertNull(equipSharedPreferencesDatasource.get().getOrNull()?.idEquip)
        assertNull(colabSharedPreferencesDatasource.get().getOrNull()?.reg)
    }

    @Test
    fun check_insert_detail_passenger_colab() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1))
        ))
        colabSharedPreferencesDatasource.setRegColab(12345L)
        
        val result = usecase("DET PASS COLAB", Option.INSERT, FlowNote.PASSENGER_COLAB, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1)
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.vehicleOwnList[0].passengerColabList[0].detail, "DET PASS COLAB")
        assertEquals(model.vehicleOwnList[0].passengerColabList[0].reg, 12345L)
        
        // Check if temporary datasource was cleaned
        assertNull(colabSharedPreferencesDatasource.get().getOrNull()?.reg)
    }

    @Test
    fun check_insert_detail_vehicle() = runTest {
        vehicleSharedPreferencesDatasource.clean()
        val result = usecase("DET VEHICLE", Option.INSERT, FlowNote.VEHICLE, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(vehicleSharedPreferencesDatasource.get().getOrNull()?.detail, "DET VEHICLE")
    }

    @Test
    fun check_insert_detail_involved() = runTest {
        involvedSharedPreferencesDatasource.setDocument("123")
        
        val result = usecase("DET INVOLVED", Option.INSERT, FlowNote.INVOLVED_EXTERNAL, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1)
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.involvedExternalList[0].detail, "DET INVOLVED")
        assertEquals(model.involvedExternalList[0].document, "123")
        
        assertNull(involvedSharedPreferencesDatasource.get().getOrNull()?.document)
    }

    @Test
    fun check_insert_detail_witness() = runTest {
        involvedSharedPreferencesDatasource.setDocument("123")
        
        val result = usecase("DET WITNESS", Option.INSERT, FlowNote.WITNESS_EXTERNAL, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1)
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.witnessExternalList[0].detail, "DET WITNESS")
        assertEquals(model.witnessExternalList[0].document, "123")
        
        assertNull(involvedSharedPreferencesDatasource.get().getOrNull()?.document)
    }

    @Test
    fun check_insert_detail_passenger_involved() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleExternalList = listOf(VehicleExternalSharedPreferencesModel(id = 1))
        ))
        involvedSharedPreferencesDatasource.setDocument("123")
        
        val result = usecase("DET PASS INV", Option.INSERT, FlowNote.PASSENGER_EXTERNAL, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1)
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.vehicleExternalList[0].passengerInvolvedList[0].detail, "DET PASS INV")
        assertEquals(model.vehicleExternalList[0].passengerInvolvedList[0].document, "123")
        
        assertNull(involvedSharedPreferencesDatasource.get().getOrNull()?.document)
    }

    @Test
    fun check_insert_detail_driver() = runTest {
        vehicleSharedPreferencesDatasource.setPlate("ABC-1234")
        involvedSharedPreferencesDatasource.setDocument("123")
        
        val result = usecase("DET DRIVER", Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), 1)
        
        val model = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(model.vehicleExternalList[0].driver.detail, "DET DRIVER")
        assertEquals(model.vehicleExternalList[0].driver.document, "123")
        assertEquals(model.vehicleExternalList[0].vehicle.plate, "ABC-1234")
        
        assertNull(vehicleSharedPreferencesDatasource.get().getOrNull()?.plate)
        assertNull(involvedSharedPreferencesDatasource.get().getOrNull()?.document)
    }

    // endregion

    // region EDIT FLOWS

    @Test
    fun check_edit_detail_equip() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(detail = "OLD")))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.EQUIP, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleOwnList[0].equip.detail, "NEW")
    }

    @Test
    fun check_edit_detail_equip_sec() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1, equipSecList = listOf(EquipCardSharedPreferencesModel(id = 10, detail = "OLD"))))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.EQUIP_SEC, 1, 10)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleOwnList[0].equipSecList[0].detail, "NEW")
    }

    @Test
    fun check_edit_detail_colab() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(detail = "OLD")))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.COLAB, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleOwnList[0].colab.detail, "NEW")
    }

    @Test
    fun check_edit_detail_passenger_colab() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(id = 1, passengerColabList = listOf(ColabCardSharedPreferencesModel(id = 10, detail = "OLD"))))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.PASSENGER_COLAB, 1, 10)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleOwnList[0].passengerColabList[0].detail, "NEW")
    }

    @Test
    fun check_edit_detail_vehicle() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleExternalList = listOf(VehicleExternalSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(detail = "OLD")))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.VEHICLE, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleExternalList[0].vehicle.detail, "NEW")
    }

    @Test
    fun check_edit_detail_driver() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleExternalList = listOf(VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(detail = "OLD")))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.DRIVER, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleExternalList[0].driver.detail, "NEW")
    }

    @Test
    fun check_edit_detail_passenger_involved() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            vehicleExternalList = listOf(VehicleExternalSharedPreferencesModel(id = 1, passengerInvolvedList = listOf(PeopleExternalSharedPreferencesModel(id = 10, detail = "OLD"))))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.PASSENGER_EXTERNAL, 1, 10)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().vehicleExternalList[0].passengerInvolvedList[0].detail, "NEW")
    }

    @Test
    fun check_edit_detail_involved() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            involvedExternalList = listOf(PeopleExternalSharedPreferencesModel(id = 1, detail = "OLD"))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().involvedExternalList[0].detail, "NEW")
    }

    @Test
    fun check_edit_detail_witness() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel(
            witnessExternalList = listOf(PeopleExternalSharedPreferencesModel(id = 1, detail = "OLD"))
        ))
        val result = usecase("NEW", Option.EDIT, FlowNote.WITNESS_EXTERNAL, 1, 0)
        assertTrue(result.isSuccess)
        assertEquals(cardSharedPreferencesDatasource.get().getOrThrow().witnessExternalList[0].detail, "NEW")
    }

    // endregion
}
