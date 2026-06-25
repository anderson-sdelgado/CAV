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
class ISetNameTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetName

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_name_insert() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase("TEST INSERT", Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGetName = involvedSharedPreferencesDatasource.get()
        assertEquals(resultGetName.getOrNull()?.name, "TEST INSERT")
    }

    @Test
    fun check_update_name_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedList = listOf(
                InvolvedSharedPreferencesModel(id = 1, name = "OLD NAME")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW NAME", Option.EDIT, FlowNote.INVOLVED, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.involvedList[0].name, "NEW NAME")
    }

    @Test
    fun check_update_name_passenger_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(id = 10, name = "OLD PASSENGER")
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW PASSENGER", Option.EDIT, FlowNote.PASSENGER_INVOLVED, 1, 10)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleInvolvedList[0].passengerInvolvedList[0].name, "NEW PASSENGER")
    }
}
