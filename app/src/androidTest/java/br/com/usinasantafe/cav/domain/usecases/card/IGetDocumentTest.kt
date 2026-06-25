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
class IGetDocumentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetDocument

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_document_insert() = runTest {
        involvedSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertEquals(result.getOrNull(), "")
        
        involvedSharedPreferencesDatasource.setDocument("123456789")
        val result2 = usecase(Option.INSERT, FlowNote.DRIVER, 0, 0)
        assertEquals(result2.getOrNull(), "123456789")
    }

    @Test
    fun check_get_document_edit_witness() = runTest {
        val data = CardSharedPreferencesModel(
            witnessList = listOf(
                InvolvedSharedPreferencesModel(id = 1, document = "987654321")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.WITNESS, 1, 0)
        assertEquals(result.getOrNull(), "987654321")
    }
}
