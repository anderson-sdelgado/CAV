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
class ISetDocumentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetDocument

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_document_insert() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase("123", Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = involvedSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.document, "123")
    }

    @Test
    fun check_update_document_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedList = listOf(
                InvolvedSharedPreferencesModel(id = 1, document = "OLD")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW", Option.EDIT, FlowNote.INVOLVED, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.involvedList[0].document, "NEW")
    }
}
