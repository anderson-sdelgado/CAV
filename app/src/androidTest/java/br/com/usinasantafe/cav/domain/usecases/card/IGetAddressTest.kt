package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
class IGetAddressTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetAddress

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_address_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    driver = InvolvedSharedPreferencesModel(address = "ADDR DRIVER")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.DRIVER, 1, 0)
        assertEquals(result.getOrNull(), "ADDR DRIVER")
    }

    @Test
    fun check_get_address_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedList = listOf(
                InvolvedSharedPreferencesModel(id = 1, address = "ADDR INVOLVED")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.INVOLVED, 1, 0)
        assertEquals(result.getOrNull(), "ADDR INVOLVED")
    }
}
