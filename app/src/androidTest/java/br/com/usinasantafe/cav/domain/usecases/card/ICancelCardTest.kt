package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ICancelCardTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: CancelCard

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_alter_data() =
        runTest {
            val data = CardSharedPreferencesModel(
                regAttendant = 19759
            )
            cardSharedPreferencesDatasource.save(data)
            val resultHasBefore = cardSharedPreferencesDatasource.has()
            assertEquals(
                resultHasBefore.isSuccess,
                true
            )
            assertEquals(
                resultHasBefore.getOrNull()!!,
                true
            )
            usecase()
            val resultHasAfter = cardSharedPreferencesDatasource.has()
            assertEquals(
                resultHasAfter.isSuccess,
                true
            )
            assertEquals(
                resultHasAfter.getOrNull()!!,
                false
            )
        }

}