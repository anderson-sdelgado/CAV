package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ISetSupportTeamsListTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetSupportTeamsList

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_alter_data_correct_in_shared_preferences() =
        runTest {
            val data = CardSharedPreferencesModel(
                idSupportTeamsList = listOf(1, 2)
            )
            cardSharedPreferencesDatasource.save(data)
            val resultGetBefore = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetBefore.isSuccess,
                true
            )
            val modelBefore = resultGetBefore.getOrNull()!!
            val listBefore = modelBefore.idSupportTeamsList
            assertEquals(
                listBefore,
                listOf(1, 2)
            )
            val result = usecase(
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 3,
                        desc = "Test3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 4,
                        desc = "Test4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 5,
                        desc = "Test5",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val resultGetAfter = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetAfter.isSuccess,
                true
            )
            val modelAfter= resultGetAfter.getOrNull()!!
            val listAfter= modelAfter.idSupportTeamsList
            assertEquals(
                listAfter,
                listOf(3, 5)
            )
        }
}