package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.*
import br.com.usinasantafe.cav.lib.State
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Provider
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IUpdateCardSharedPreferencesDatasourceTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cardDatasource: CardSharedPreferencesDatasource
    private lateinit var datasource: IUpdateCardSharedPreferencesDatasource

    class TestProvider<T : Any> : Provider<T> {
        lateinit var value: T
        override fun get(): T = value
    }

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()

        val provider = TestProvider<CardSharedPreferencesDatasource>()
        datasource = IUpdateCardSharedPreferencesDatasource(provider)

        cardDatasource = ICardSharedPreferencesDatasource(
            basicCardSharedPreferencesDatasource = mock(),
            insertCardSharedPreferencesDatasource = mock(),
            recoverDataCardSharedPreferencesDatasource = mock(),
            updateCardSharedPreferencesDatasource = datasource,
            deleteCardSharedPreferencesDatasource = mock(),
            sharedPreferences = sharedPreferences
        )

        provider.value = cardDatasource
    }

    @Test
    fun `updateIdEquip - Check update data correct`() =
        runTest {
            val list = listOf(VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(idEquip = 10)))
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].equip.idEquip, 10)

            val result = datasource.updateIdEquip(20, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].equip.idEquip, 20)
        }

    @Test
    fun `updateIdEquipSecondary - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    equipSecList = listOf(EquipCardSharedPreferencesModel(id = 10, idEquip = 100))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].equipSecList[0].idEquip, 100)

            val result = datasource.updateIdEquipSecondary(200, 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].equipSecList[0].idEquip, 200)
        }

    @Test
    fun `updateDetailEquip - Check update data correct`() =
        runTest {
            val list = listOf(VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(detail = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].equip.detail, "old")

            val result = datasource.updateDetailEquip("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].equip.detail, "new")
        }

    @Test
    fun `updateDetailEquipSecondary - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    equipSecList = listOf(EquipCardSharedPreferencesModel(id = 10, detail = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].equipSecList[0].detail, "old")

            val result = datasource.updateDetailEquipSecondary("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].equipSecList[0].detail, "new")
        }

    @Test
    fun `updateDetailColab - Check update data correct`() =
        runTest {
            val list = listOf(VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(detail = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].colab.detail, "old")

            val result = datasource.updateDetailColab("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].colab.detail, "new")
        }

    @Test
    fun `updateDetailDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(detail = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.detail, "old")

            val result = datasource.updateDetailDriver("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.detail, "new")
        }

    @Test
    fun `updateDetailPassengerColab - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(ColabCardSharedPreferencesModel(id = 10, detail = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].passengerColabList[0].detail, "old")

            val result = datasource.updateDetailPassengerColab("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].passengerColabList[0].detail, "new")
        }

    @Test
    fun `updateDetailVehicle - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(detail = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].vehicle.detail, "old")

            val result = datasource.updateDetailVehicle("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].vehicle.detail, "new")
        }

    @Test
    fun `updateDetailPassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, detail = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].detail, "old")

            val result = datasource.updateDetailPassengerInvolved("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].detail, "new")
        }

    @Test
    fun `updateDetailInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, detail = "old"))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].detail, "old")

            val result = datasource.updateDetailInvolved("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].detail, "new")
        }

    @Test
    fun `updateDetailWitness - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, detail = "old"))
            cardDatasource.save(CardSharedPreferencesModel(witnessList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.witnessList[0].detail, "old")

            val result = datasource.updateDetailWitness("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.witnessList[0].detail, "new")
        }

    @Test
    fun `updateRegColab - Check update data correct`() =
        runTest {
            val list = listOf(VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(reg = 10L)))
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].colab.reg, 10L)

            val result = datasource.updateRegColab(20L, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].colab.reg, 20L)
        }

    @Test
    fun `updateRegPassengerColab - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(ColabCardSharedPreferencesModel(id = 10, reg = 100L))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].passengerColabList[0].reg, 100L)

            val result = datasource.updateRegPassengerColab(200L, 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].passengerColabList[0].reg, 200L)
        }

    @Test
    fun `updateStateColab - Check update data correct`() =
        runTest {
            val list = listOf(VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(state = State.UNHARMED)))
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].colab.state, State.UNHARMED)

            val result = datasource.updateStateColab(State.DEAD, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].colab.state, State.DEAD)
        }

    @Test
    fun `updateStatePassengerColab - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(ColabCardSharedPreferencesModel(id = 10, state = State.UNHARMED))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleOwnList[0].passengerColabList[0].state, State.UNHARMED)

            val result = datasource.updateStatePassengerColab(State.DEAD, 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleOwnList[0].passengerColabList[0].state, State.DEAD)
        }

    @Test
    fun `updateStateDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(state = State.UNHARMED)))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.state, State.UNHARMED)

            val result = datasource.updateStateDriver(State.DEAD, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.state, State.DEAD)
        }

    @Test
    fun `updateStatePassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, state = State.UNHARMED))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].state, State.UNHARMED)

            val result = datasource.updateStatePassengerInvolved(State.DEAD, 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].state, State.DEAD)
        }

    @Test
    fun `updateStateInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, state = State.UNHARMED))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].state, State.UNHARMED)

            val result = datasource.updateStateInvolved(State.DEAD, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].state, State.DEAD)
        }

    @Test
    fun `updateStateWitness - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, state = State.UNHARMED))
            cardDatasource.save(CardSharedPreferencesModel(witnessList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.witnessList[0].state, State.UNHARMED)

            val result = datasource.updateStateWitness(State.DEAD, 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.witnessList[0].state, State.DEAD)
        }

    @Test
    fun `updateAddressPassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, address = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].address, "old")

            val result = datasource.updateAddressPassengerInvolved("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].address, "new")
        }

    @Test
    fun `updateAddressInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, address = "old"))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].address, "old")

            val result = datasource.updateAddressInvolved("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].address, "new")
        }

    @Test
    fun `updateAddressDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(address = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.address, "old")

            val result = datasource.updateAddressDriver("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.address, "new")
        }

    @Test
    fun `updateBrand - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(brand = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].vehicle.brand, "old")

            val result = datasource.updateBrand("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].vehicle.brand, "new")
        }

    @Test
    fun `updatePlate - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(plate = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].vehicle.plate, "old")

            val result = datasource.updatePlate("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].vehicle.plate, "new")
        }

    @Test
    fun `updateDocumentDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(document = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.document, "old")

            val result = datasource.updateDocumentDriver("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.document, "new")
        }

    @Test
    fun `updateDocumentPassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, document = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].document, "old")

            val result = datasource.updateDocumentPassengerInvolved("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].document, "new")
        }

    @Test
    fun `updateDocumentInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, document = "old"))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].document, "old")

            val result = datasource.updateDocumentInvolved("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].document, "new")
        }

    @Test
    fun `updateNameDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(name = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.name, "old")

            val result = datasource.updateNameDriver("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.name, "new")
        }

    @Test
    fun `updateNamePassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, name = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].name, "old")

            val result = datasource.updateNamePassengerInvolved("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].name, "new")
        }

    @Test
    fun `updateNameInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, name = "old"))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].name, "old")

            val result = datasource.updateNameInvolved("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].name, "new")
        }

    @Test
    fun `updateNameWitness - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, name = "old"))
            cardDatasource.save(CardSharedPreferencesModel(witnessList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.witnessList[0].name, "old")

            val result = datasource.updateNameWitness("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.witnessList[0].name, "new")
        }

    @Test
    fun `updatePhoneDriver - Check update data correct`() =
        runTest {
            val list = listOf(VehicleInvolvedSharedPreferencesModel(id = 1, driver = InvolvedSharedPreferencesModel(phone = "old")))
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].driver.phone, "old")

            val result = datasource.updatePhoneDriver("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].driver.phone, "new")
        }

    @Test
    fun `updatePhoneInvolved - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, phone = "old"))
            cardDatasource.save(CardSharedPreferencesModel(involvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.involvedList[0].phone, "old")

            val result = datasource.updatePhoneInvolved("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.involvedList[0].phone, "new")
        }

    @Test
    fun `updatePhoneWitness - Check update data correct`() =
        runTest {
            val list = listOf(InvolvedSharedPreferencesModel(id = 1, phone = "old"))
            cardDatasource.save(CardSharedPreferencesModel(witnessList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.witnessList[0].phone, "old")

            val result = datasource.updatePhoneWitness("new", 1)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.witnessList[0].phone, "new")
        }

    @Test
    fun `updatePhonePassengerInvolved - Check update data correct`() =
        runTest {
            val list = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1, 
                    passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(id = 10, phone = "old"))
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleInvolvedList = list))

            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(modelBefore.vehicleInvolvedList[0].passengerInvolvedList[0].phone, "old")

            val result = datasource.updatePhonePassengerInvolved("new", 1, 10)
            assertEquals(result.isSuccess, true)
            
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].phone, "new")
        }

}
