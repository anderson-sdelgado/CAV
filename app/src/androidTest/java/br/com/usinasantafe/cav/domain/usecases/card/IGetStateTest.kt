package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.State
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IGetStateTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetState

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_state_insert_colab() = runTest {
        colabSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.COLAB, 0, 0)
        assertEquals(result.getOrNull(), State.UNHARMED)
        
        colabSharedPreferencesDatasource.setState(State.DEAD)
        val result2 = usecase(Option.INSERT, FlowNote.COLAB, 0, 0)
        assertEquals(result2.getOrNull(), State.DEAD)
    }

    @Test
    fun check_get_state_insert_involved() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.INVOLVED_EXTERNAL, 0, 0)
        assertEquals(result.getOrNull(), State.UNHARMED)
        
        involvedSharedPreferencesDatasource.setState(State.INJURED)
        val result2 = usecase(Option.INSERT, FlowNote.INVOLVED_EXTERNAL, 0, 0)
        assertEquals(result2.getOrNull(), State.INJURED)
    }

    @Test
    fun check_get_state_edit_driver() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleExternalList = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    driver = PeopleExternalSharedPreferencesModel(state = State.DEAD)
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.DRIVER, 1, 0)
        assertEquals(result.getOrNull(), State.DEAD)
    }
}
