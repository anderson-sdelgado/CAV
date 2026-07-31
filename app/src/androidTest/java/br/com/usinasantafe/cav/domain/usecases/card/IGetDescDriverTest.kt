package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.FlowNote
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IGetDescDriverTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetDescDriver

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_desc_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    driver = PeopleExternalSharedPreferencesModel(document = "123", name = "DRIVER TEST")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.DRIVER, 1)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "123 - DRIVER TEST")
    }
}
