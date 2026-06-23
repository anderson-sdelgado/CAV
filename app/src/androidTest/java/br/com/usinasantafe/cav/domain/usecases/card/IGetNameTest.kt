package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
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
class IGetNameTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetName

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_name_insert() = runTest {
        cardSharedPreferencesDatasource.save(CardSharedPreferencesModel())
        // Como o ICardSharedPreferencesDatasource.getName() (usado no INSERT) 
        // busca do InvolvedSharedPreferencesDatasource, o teste instrumentado 
        // vai testar a integração real.
        val result = usecase(Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "")
    }

    @Test
    fun check_get_name_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedList = listOf(
                InvolvedSharedPreferencesModel(id = 1, name = "TEST INVOLVED")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        val result = usecase(Option.EDIT, FlowNote.INVOLVED, 1, 0)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "TEST INVOLVED")
    }

    @Test
    fun check_get_name_passenger_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(id = 10, name = "PASSENGER TEST")
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        val result = usecase(Option.EDIT, FlowNote.PASSENGER_INVOLVED, 1, 10)
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), "PASSENGER TEST")
    }
}
