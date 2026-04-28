package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals

@HiltAndroidTest
class ISetFinishUpdateAllTableTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ISetFinishUpdateAllTable

    @Inject
    lateinit var configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun check_altered_if_have_data() =
        runTest {
            val model = ConfigSharedPreferencesModel(
                number = 16997417840,
                password = "123456",
                idServ = 1,
                version = "1.00"
            )
            configSharedPreferencesDatasource.save(model)
            val resultGetBefore = configSharedPreferencesDatasource.get()
            assertEquals(
                resultGetBefore.isSuccess,
                true
            )
            val modelBefore = resultGetBefore.getOrNull()!!
            assertEquals(
                modelBefore.flagUpdate,
                false
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            val resultGetAfter = configSharedPreferencesDatasource.get()
            assertEquals(
                resultGetAfter.isSuccess,
                true
            )
            val modelAfter = resultGetAfter.getOrNull()!!
            assertEquals(
                modelAfter.flagUpdate,
                true
            )
        }

}