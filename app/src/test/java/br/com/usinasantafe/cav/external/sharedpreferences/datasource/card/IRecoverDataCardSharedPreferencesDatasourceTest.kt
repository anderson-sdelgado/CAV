package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
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
class IRecoverDataCardSharedPreferencesDatasourceTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cardDatasource: CardSharedPreferencesDatasource
    private lateinit var datasource: IRecoverDataCardSharedPreferencesDatasource

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
        datasource = IRecoverDataCardSharedPreferencesDatasource(provider)

        cardDatasource = ICardSharedPreferencesDatasource(
            basicCardSharedPreferencesDatasource = mock(),
            insertCardSharedPreferencesDatasource = mock(),
            recoverDataCardSharedPreferencesDatasource = datasource,
            updateCardSharedPreferencesDatasource = mock(),
            deleteCardSharedPreferencesDatasource = mock(),
            sharedPreferences = sharedPreferences
        )

        provider.value = cardDatasource
    }

    @Test
    fun `getIdEquip - Check return correct idEquip`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(idEquip = 10)),
                VehicleOwnSharedPreferencesModel(id = 2, equip = EquipCardSharedPreferencesModel(idEquip = 20))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getIdEquip(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 10)
            
            val result2 = datasource.getIdEquip(2)
            assertEquals(result2.getOrNull(), 20)
        }

    @Test
    fun `getIdEquipSecondary - Check return correct idEquip from equipSecList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(id = 10, idEquip = 100),
                        EquipCardSharedPreferencesModel(id = 20, idEquip = 200)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getIdEquipSecondary(1, 10)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 100)
            
            val result2 = datasource.getIdEquipSecondary(1, 20)
            assertEquals(result2.getOrNull(), 200)
        }

    @Test
    fun `getDetailEquip - Check return correct detail`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(detail = "detail 1")),
                VehicleOwnSharedPreferencesModel(id = 2, equip = EquipCardSharedPreferencesModel(detail = "detail 2"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getDetailEquip(1)
            assertEquals(result.getOrNull(), "detail 1")
        }

    @Test
    fun `getDetailEquipSecondary - Check return correct detail from equipSecList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(id = 10, detail = "sec detail 1")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getDetailEquipSecondary(1, 10)
            assertEquals(result.getOrNull(), "sec detail 1")
        }

    @Test
    fun `getDetailColab - Check return correct detail from colab`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(detail = "colab detail"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getDetailColab(1)
            assertEquals(result.getOrNull(), "colab detail")
        }

    @Test
    fun `getDetailPassengerColab - Check return correct detail from passengerColabList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(id = 10, detail = "pass detail")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getDetailPassengerColab(1, 10)
            assertEquals(result.getOrNull(), "pass detail")
        }

    @Test
    fun `getDetailVehicle - Check return correct detail from vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(detail = "veh detail"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getDetailVehicle(1)
            assertEquals(result.getOrNull(), "veh detail")
        }

    @Test
    fun `getDetailDriver - Check return correct detail from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(detail = "driver detail"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getDetailDriver(1)
            assertEquals(result.getOrNull(), "driver detail")
        }

    @Test
    fun `getDetailPassengerInvolved - Check return correct detail from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, detail = "pass inv detail")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getDetailPassengerExternal(1, 10)
            assertEquals(result.getOrNull(), "pass inv detail")
        }

    @Test
    fun `getDetailInvolved - Check return correct detail from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, detail = "inv detail")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getDetailInvolvedExternal(1)
            assertEquals(result.getOrNull(), "inv detail")
        }

    @Test
    fun `getDetailWitness - Check return correct detail from witnessList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, detail = "wit detail")
            )
            cardDatasource.save(CardSharedPreferencesModel(witnessExternalList = list))
            
            val result = datasource.getDetailWitnessExternal(1)
            assertEquals(result.getOrNull(), "wit detail")
        }

    @Test
    fun `getRegColab - Check return correct reg`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(reg = 123456L))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getRegColab(1)
            assertEquals(result.getOrNull(), 123456L)
        }

    @Test
    fun `getRegPassengerColab - Check return correct reg from passengerColabList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(id = 10, reg = 654321L)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getRegPassengerColab(1, 10)
            assertEquals(result.getOrNull(), 654321L)
        }

    @Test
    fun `getStateColab - Check return correct state`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(state = State.DEAD))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getStateColab(1)
            assertEquals(result.getOrNull(), State.DEAD)
        }

    @Test
    fun `getStatePassengerColab - Check return correct state from passengerColabList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(id = 10, state = State.INJURED)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getStatePassengerColab(1, 10)
            assertEquals(result.getOrNull(), State.INJURED)
        }

    @Test
    fun `getStatePassengerInvolved - Check return correct state from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, state = State.DEAD)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getStatePassengerInvolved(1, 10)
            assertEquals(result.getOrNull(), State.DEAD)
        }

    @Test
    fun `getStateInvolved - Check return correct state from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, state = State.INJURED)
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getStateInvolvedExternal(1)
            assertEquals(result.getOrNull(), State.INJURED)
        }

    @Test
    fun `getStateDriver - Check return correct state from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(state = State.UNHARMED))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getStateDriver(1)
            assertEquals(result.getOrNull(), State.UNHARMED)
        }

    @Test
    fun `getAddressPassengerInvolved - Check return correct address from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, address = "pass address")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getAddressPassengerExternal(1, 10)
            assertEquals(result.getOrNull(), "pass address")
        }

    @Test
    fun `getAddressDriver - Check return correct address from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(address = "driver address"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getAddressDriver(1)
            assertEquals(result.getOrNull(), "driver address")
        }

    @Test
    fun `getAddressInvolved - Check return correct address from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, address = "involved address")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getAddressInvolved(1)
            assertEquals(result.getOrNull(), "involved address")
        }

    @Test
    fun `getBrand - Check return correct brand from vehicle in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(brand = "FORD"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getBrand(1)
            assertEquals(result.getOrNull(), "FORD")
        }

    @Test
    fun `getPlate - Check return correct plate from vehicle in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(plate = "ABC-1234"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getPlate(1)
            assertEquals(result.getOrNull(), "ABC-1234")
        }

    @Test
    fun `getDocumentDriver - Check return correct document from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(document = "123.456.789-00"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getDocumentDriver(1)
            assertEquals(result.getOrNull(), "123.456.789-00")
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return correct document from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, document = "000.000.000-00")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getDocumentPassengerExternal(1, 10)
            assertEquals(result.getOrNull(), "000.000.000-00")
        }

    @Test
    fun `getNameDriver - Check return correct name from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(name = "DRIVER NAME"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getNameDriver(1)
            assertEquals(result.getOrNull(), "DRIVER NAME")
        }

    @Test
    fun `getNamePassengerInvolved - Check return correct name from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, name = "PASSENGER NAME")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getNamePassengerExternal(1, 10)
            assertEquals(result.getOrNull(), "PASSENGER NAME")
        }

    @Test
    fun `listIdEquipSecondary - Check return correct list of IDs from equipSecList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(id = 10),
                        EquipCardSharedPreferencesModel(id = 20)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.listEquipSecondary(1)
            assertEquals(result.getOrNull(), listOf(EquipCard(id = 10), EquipCard(id = 20)))
        }

    @Test
    fun `listPassengerColab - Check return correct list of ColabCard from passengerColabList`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1, 
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(id = 10, reg = 123L),
                        ColabCardSharedPreferencesModel(id = 20, reg = 456L)
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.listPassengerColab(1)
            val passList = result.getOrNull()!!
            assertEquals(passList.size, 2)
            assertEquals(passList[0].id, 10)
            assertEquals(passList[0].reg, 123L)
        }

    @Test
    fun `listPassengerInvolved - Check return correct list of Involved from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, name = "P1"),
                        PeopleExternalSharedPreferencesModel(id = 20, name = "P2")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.listPassengerExternal(1)
            val passList = result.getOrNull()!!
            assertEquals(passList.size, 2)
            assertEquals(passList[0].id, 10)
            assertEquals(passList[0].name, "P1")
        }

    @Test
    fun `listInvolved - Check return correct list of Involved from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, name = "I1"),
                PeopleExternalSharedPreferencesModel(id = 2, name = "I2")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.listInvolvedExternal()
            val invList = result.getOrNull()!!
            assertEquals(invList.size, 2)
            assertEquals(invList[0].id, 1)
            assertEquals(invList[0].name, "I1")
        }

    @Test
    fun `listWitness - Check return correct list of Involved from witnessList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, name = "W1"),
                PeopleExternalSharedPreferencesModel(id = 2, name = "W2")
            )
            cardDatasource.save(CardSharedPreferencesModel(witnessExternalList = list))
            
            val result = datasource.listWitnessExternal()
            val witList = result.getOrNull()!!
            assertEquals(witList.size, 2)
            assertEquals(witList[0].id, 1)
            assertEquals(witList[0].name, "W1")
        }

    @Test
    fun `getDocumentInvolved - Check return correct document from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, document = "DOC1")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getDocumentInvolved(1)
            assertEquals(result.getOrNull(), "DOC1")
        }

    @Test
    fun `getNameInvolved - Check return correct name from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, name = "NAME1")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getNameInvolved(1)
            assertEquals(result.getOrNull(), "NAME1")
        }

    @Test
    fun `getNameWitness - Check return correct name from witnessList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, name = "WITNAME1")
            )
            cardDatasource.save(CardSharedPreferencesModel(witnessExternalList = list))
            
            val result = datasource.getNameWitness(1)
            assertEquals(result.getOrNull(), "WITNAME1")
        }

    @Test
    fun `getPhoneDriver - Check return correct phone from driver in vehicleInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, driver = PeopleExternalSharedPreferencesModel(phone = "PH1"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getPhoneDriver(1)
            assertEquals(result.getOrNull(), "PH1")
        }

    @Test
    fun `getPhoneInvolved - Check return correct phone from involvedList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, phone = "PHINV1")
            )
            cardDatasource.save(CardSharedPreferencesModel(involvedExternalList = list))
            
            val result = datasource.getPhoneInvolved(1)
            assertEquals(result.getOrNull(), "PHINV1")
        }

    @Test
    fun `getPhoneWitness - Check return correct phone from witnessList`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, phone = "PHWIT1")
            )
            cardDatasource.save(CardSharedPreferencesModel(witnessExternalList = list))
            
            val result = datasource.getPhoneWitness(1)
            assertEquals(result.getOrNull(), "PHWIT1")
        }

    @Test
    fun `getPhonePassengerInvolved - Check return correct phone from passengerInvolvedList`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 10, phone = "PHPASS1")
                    )
                )
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.getPhonePassengerInvolved(1, 10)
            assertEquals(result.getOrNull(), "PHPASS1")
        }

    @Test
    fun `listVehicleOwn - Check return correct list of VehicleOwn`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, equip = EquipCardSharedPreferencesModel(idEquip = 10)),
                VehicleOwnSharedPreferencesModel(id = 2, equip = EquipCardSharedPreferencesModel(idEquip = 20))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.listVehicleOwn()
            val vehList = result.getOrNull()!!
            assertEquals(vehList.size, 2)
            assertEquals(vehList[0].id, 1)
            assertEquals(vehList[0].equipCard.idEquip, 10)
        }

    @Test
    fun `listVehicleInvolved - Check return correct list of VehicleInvolved`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(id = 1, vehicle = VehicleSharedPreferencesModel(plate = "P1")),
                VehicleExternalSharedPreferencesModel(id = 2, vehicle = VehicleSharedPreferencesModel(plate = "P2"))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = list))
            
            val result = datasource.listVehicleExternal()
            val vehList = result.getOrNull()!!
            assertEquals(vehList.size, 2)
            assertEquals(vehList[0].id, 1)
            assertEquals(vehList[0].vehicle.plate, "P1")
        }

    @Test
    fun `getResultBreathalyzer - Check return correct value`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(flagResultBreathalyzer = true))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getResultBreathalyzer(1)
            assertEquals(result.getOrNull(), true)
        }

    @Test
    fun `getRealizedBreathalyzer - Check return correct value`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(flagRealizedBreathalyzer = false))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getRealizedBreathalyzer(1)
            assertEquals(result.getOrNull(), false)
        }

    @Test
    fun `getCountBreathalyzer - Check return correct value`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(countBreathalyzer = 0.5))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getCountBreathalyzer(1)
            assertEquals(result.getOrNull(), 0.5)
        }

    @Test
    fun `getBreathalyzer - Check return correct Triple`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(id = 1, colab = ColabCardSharedPreferencesModel(
                    flagResultBreathalyzer = true,
                    flagRealizedBreathalyzer = true,
                    countBreathalyzer = 0.15
                ))
            )
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = list))
            
            val result = datasource.getBreathalyzer(1)
            assertEquals(result.getOrNull(), Triple(true, true, 0.15))
        }

}
