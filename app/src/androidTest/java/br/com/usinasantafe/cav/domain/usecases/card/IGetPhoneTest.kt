package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
class IGetPhoneTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetPhone

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_phone_insert() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.INVOLVED, 0, 0)
        assertEquals(result.getOrNull(), "")
        
        involvedSharedPreferencesDatasource.setPhone("16999999999")
        val result2 = usecase(Option.INSERT, FlowNote.INVOLVED, 0, 0)
        assertEquals(result2.getOrNull(), "16999999999")
    }

    @Test
    fun check_get_phone_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    driver = InvolvedSharedPreferencesModel(phone = "11888888888")
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.DRIVER, 1, 0)
        assertEquals(result.getOrNull(), "11888888888")
    }

    @Test
    fun check_get_phone_passenger_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(id = 10, phone = "11777777777")
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.PASSENGER_INVOLVED, 1, 10)
        assertEquals(result.getOrNull(), "11777777777")
    }
}
