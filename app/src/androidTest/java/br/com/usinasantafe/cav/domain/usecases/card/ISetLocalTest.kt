package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ISetLocalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetLocal

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_data_altered_if_process_execute_successfully() =
        runTest {
            val data = CardSharedPreferencesModel(
                local = LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            cardSharedPreferencesDatasource.save(data)
            val resultBefore = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultBefore.isSuccess,
                true
            )
            val modelBefore = resultBefore.getOrNull()!!
            assertEquals(
                modelBefore.local!!.address,
                "Test"
            )
            assertEquals(
                modelBefore.local!!.latitude,
                0.0
            )
            assertEquals(
                modelBefore.local!!.longitude,
                0.0
            )
            val result = usecase(
                address = "Test2",
                latitude = 1.0,
                longitude = 1.0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val resultAfter = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultAfter.isSuccess,
                true
            )
            val modelAfter = resultAfter.getOrNull()!!
            assertEquals(
                modelAfter.local!!.address,
                "Test2"
            )
            assertEquals(
                modelAfter.local!!.latitude,
                1.0
            )
            assertEquals(
                modelAfter.local!!.longitude,
                1.0
            )
        }
}