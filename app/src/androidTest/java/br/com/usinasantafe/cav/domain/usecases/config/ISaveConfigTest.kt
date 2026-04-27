package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.IConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ISaveConfigTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SaveConfig

    @Inject
    lateinit var configSharedPreferencesDatasource: IConfigSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_failure_if_number_input_is_incorrect() =
        runTest {

            val result = usecase("dfas6fdsa7840", "12345", "1.00", idServ = 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISaveConfig -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"dfas6fdsa7840\""
            )
        }

    @Test
    fun check_alter_data_if_process_execute_successfully() =
        runTest {
            val resultGetBefore = configSharedPreferencesDatasource.get()
            assertEquals(
                resultGetBefore.isSuccess,
                true
            )
            val modelGetBefore = resultGetBefore.getOrNull()!!
            assertEquals(
                modelGetBefore,
                ConfigSharedPreferencesModel()
            )
            val result = usecase("16997417840", "12345", "1.00", idServ = 1)
            assertEquals(
                result.isSuccess,
                true
            )
            val resultGetAfter = configSharedPreferencesDatasource.get()
            assertEquals(
                resultGetAfter.isSuccess,
                true
            )
            val modelGetAfter = resultGetAfter.getOrNull()!!
            assertEquals(
                modelGetAfter,
                ConfigSharedPreferencesModel(
                    number = 16997417840,
                    password = "12345",
                    version = "1.00",
                    idServ = 1
                )
            )
        }

}