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
class IGetBrandTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetBrand

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_brand_insert() = runTest {
        vehicleSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, 0)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "")
        
        vehicleSharedPreferencesDatasource.setBrand("FORD")
        val result2 = usecase(Option.INSERT, 0)
        assertEquals(result2.getOrNull(), "FORD")
    }

    @Test
    fun check_get_brand_edit() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    vehicle = VehicleSharedPreferencesModel(brand = "FIAT")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, 1)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "FIAT")
    }
}
