package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IGetDescVehicleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetDescVehicle

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_desc_vehicle() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    vehicle = VehicleSharedPreferencesModel(plate = "ABC", brand = "FORD")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(1)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "ABC - FORD")
    }
}
