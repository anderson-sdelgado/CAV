package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import br.com.usinasantafe.cav.lib.Option
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ISetPlateTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetPlate

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_plate_insert() = runTest {
        vehicleSharedPreferencesDatasource.clean()
        val result = usecase("ABC-1234", Option.INSERT, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = vehicleSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.plate, "ABC-1234")
    }

    @Test
    fun check_update_plate_vehicle_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    vehicle = VehicleSharedPreferencesModel(plate = "OLD-0000")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW-9999", Option.EDIT, 1)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleExternalList[0].vehicle.plate, "NEW-9999")
    }
}
