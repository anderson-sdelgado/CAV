package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
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
class IInsertCardSharedPreferencesDatasourceTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cardDatasource: CardSharedPreferencesDatasource
    private lateinit var datasource: IInsertCardSharedPreferencesDatasource

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
        datasource = IInsertCardSharedPreferencesDatasource(provider)

        cardDatasource = ICardSharedPreferencesDatasource(
            basicCardSharedPreferencesDatasource = mock(),
            insertCardSharedPreferencesDatasource = datasource,
            recoverDataCardSharedPreferencesDatasource = mock(),
            updateCardSharedPreferencesDatasource = mock(),
            deleteCardSharedPreferencesDatasource = mock(),
            sharedPreferences = sharedPreferences
        )

        provider.value = cardDatasource
    }

    @Test
    fun `addVehicleOwn - Check insert data correct and return id`() =
        runTest {
            val entity = VehicleOwnSharedPreferencesModel()
            val result = datasource.addVehicleOwn(entity)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.vehicleOwnList.size, 1)
            assertEquals(model.vehicleOwnList[0].id, 1)
            
            val entity2 = VehicleOwnSharedPreferencesModel()
            val result2 = datasource.addVehicleOwn(entity2)
            assertEquals(result2.getOrNull(), 2)
            
            val model2 = cardDatasource.get().getOrThrow()
            assertEquals(model2.vehicleOwnList.size, 2)
            assertEquals(model2.vehicleOwnList[1].id, 2)
        }

    @Test
    fun `addEquipSec - Check insert data correct and return id`() =
        runTest {
            val vehicleOwn = VehicleOwnSharedPreferencesModel(id = 1)
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = listOf(vehicleOwn)))
            
            val equip = EquipCardSharedPreferencesModel()
            val result = datasource.addEquipSec(equip, 1)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.vehicleOwnList[0].equipSecList.size, 1)
            assertEquals(model.vehicleOwnList[0].equipSecList[0].id, 1)
        }

    @Test
    fun `addPassengerColab - Check insert data correct and return id`() =
        runTest {
            val vehicleOwn = VehicleOwnSharedPreferencesModel(id = 1)
            cardDatasource.save(CardSharedPreferencesModel(vehicleOwnList = listOf(vehicleOwn)))
            
            val colab = ColabCardSharedPreferencesModel()
            val result = datasource.addPassengerColab(colab, 1)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.vehicleOwnList[0].passengerColabList.size, 1)
            assertEquals(model.vehicleOwnList[0].passengerColabList[0].id, 1)
        }

    @Test
    fun `addVehicleInvolved - Check insert data correct and return id`() =
        runTest {
            val entity = VehicleExternalSharedPreferencesModel()
            val result = datasource.addVehicleExternal(entity)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.vehicleExternalList.size, 1)
            assertEquals(model.vehicleExternalList[0].id, 1)
        }

    @Test
    fun `addInvolved - Check insert data correct and return id`() =
        runTest {
            val entity = PeopleExternalSharedPreferencesModel()
            val result = datasource.addInvolvedExternal(entity)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.involvedExternalList.size, 1)
            assertEquals(model.involvedExternalList[0].id, 1)
        }

    @Test
    fun `addWitness - Check insert data correct and return id`() =
        runTest {
            val entity = PeopleExternalSharedPreferencesModel()
            val result = datasource.addWitnessExternal(entity)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.witnessExternalList.size, 1)
            assertEquals(model.witnessExternalList[0].id, 1)
        }

    @Test
    fun `addPassengerInvolved - Check insert data correct and return id`() =
        runTest {
            val vehicleInvolved = VehicleExternalSharedPreferencesModel(id = 1)
            cardDatasource.save(CardSharedPreferencesModel(vehicleExternalList = listOf(vehicleInvolved)))
            
            val entity = PeopleExternalSharedPreferencesModel()
            val result = datasource.addPassengerExternal(entity, 1)
            
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
            
            val model = cardDatasource.get().getOrThrow()
            assertEquals(model.vehicleExternalList[0].passengerInvolvedList.size, 1)
            assertEquals(model.vehicleExternalList[0].passengerInvolvedList[0].id, 1)
        }
}
