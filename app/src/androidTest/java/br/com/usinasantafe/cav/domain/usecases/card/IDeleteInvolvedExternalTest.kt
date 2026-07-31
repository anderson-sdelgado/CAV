package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
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
class IDeleteInvolvedExternalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: DeleteInvolvedExternal

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_delete_passenger_colab() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(id = 10)
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.PASSENGER_COLAB, 1, 10)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleOwnList[0].passengerColabList.size, 0)
    }

    @Test
    fun check_delete_passenger_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(id = 20)
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.PASSENGER_EXTERNAL, 1, 20)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleExternalList[0].passengerInvolvedList.size, 0)
    }
}
