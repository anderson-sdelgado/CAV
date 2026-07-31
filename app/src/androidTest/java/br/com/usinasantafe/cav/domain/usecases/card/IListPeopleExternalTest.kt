package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IListPeopleExternalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListInvolvedExternal

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_list_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedExternalList = listOf(
                PeopleExternalSharedPreferencesModel(id = 1, document = "123", name = "I1"),
                PeopleExternalSharedPreferencesModel(id = 2, document = "456", name = "I2")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase()
        val list = result.getOrNull()!!
        assertEquals(list.size, 2)
        assertEquals(list[0], ItemListScreenModel(id = 1, desc = "123 - I1"))
        assertEquals(list[1], ItemListScreenModel(id = 2, desc = "456 - I2"))
    }
}
