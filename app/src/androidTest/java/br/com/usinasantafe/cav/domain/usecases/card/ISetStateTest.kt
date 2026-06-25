package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
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
class ISetStateTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetState

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
    fun check_set_state_insert_colab() = runTest {
        colabSharedPreferencesDatasource.clean()
        val result = usecase(State.DEAD, Option.INSERT, FlowNote.COLAB, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = colabSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.state, State.DEAD)
    }

    @Test
    fun check_set_state_insert_involved() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase(State.INJURED, Option.INSERT, FlowNote.INVOLVED, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = involvedSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.state, State.INJURED)
    }

    @Test
    fun check_update_state_witness() = runTest {
        val data = CardSharedPreferencesModel(
            witnessList = listOf(
                InvolvedSharedPreferencesModel(id = 1, state = State.UNHARMED)
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(State.DEAD, Option.EDIT, FlowNote.WITNESS, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.witnessList[0].state, State.DEAD)
    }
}
