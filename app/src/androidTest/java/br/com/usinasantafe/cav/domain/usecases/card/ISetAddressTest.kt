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
class ISetAddressTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetAddress

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_update_address_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    driver = PeopleExternalSharedPreferencesModel(address = "OLD ADDR")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW ADDR", FlowNote.DRIVER, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleExternalList[0].driver.address, "NEW ADDR")
    }

    @Test
    fun check_update_address_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedExternalList = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, address = "OLD ADDR")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW ADDR", FlowNote.INVOLVED_EXTERNAL, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.involvedExternalList[0].address, "NEW ADDR")
    }
}
