package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.FlowNote
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
class ISetPhoneTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetPhone

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_update_phone_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    driver = PeopleExternalSharedPreferencesModel(phone = "OLD")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW", Option.INSERT, FlowNote.DRIVER, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleExternalList[0].driver.phone, "NEW")
    }

    @Test
    fun check_update_phone_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedExternalList = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, phone = "OLD")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW", Option.INSERT, FlowNote.INVOLVED_EXTERNAL, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.involvedExternalList[0].phone, "NEW")
    }
}
