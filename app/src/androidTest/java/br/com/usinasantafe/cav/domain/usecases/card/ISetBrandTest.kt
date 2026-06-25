package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
class ISetBrandTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetBrand

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_brand_insert() = runTest {
        vehicleSharedPreferencesDatasource.clean()
        val result = usecase("FORD", Option.INSERT, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = vehicleSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.brand, "FORD")
    }

    @Test
    fun check_update_brand_vehicle_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    vehicle = VehicleSharedPreferencesModel(brand = "FIAT")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("VW", Option.EDIT, 1)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleInvolvedList[0].vehicle.brand, "VW")
    }
}
