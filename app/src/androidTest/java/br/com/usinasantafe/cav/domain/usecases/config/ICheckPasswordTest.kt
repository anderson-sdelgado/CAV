package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.IConfigSharedPreferencesDatasource
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
class ICheckPasswordTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: CheckPassword

    @Inject
    lateinit var configSharedPreferencesDatasource: IConfigSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_true_if_not_have_data() =
        runTest {
            val result = usecase("12345")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun check_return_true_if_password_is_correct() =
        runTest {
            configSharedPreferencesDatasource.save(
                ConfigSharedPreferencesModel(
                    idServ = 1,
                    version = "1.00",
                    number = 123456,
                    password = "12345"
                )
            )
            val result = usecase("12345")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun check_return_false_if_password_is_incorrect() =
        runTest {
            configSharedPreferencesDatasource.save(
                ConfigSharedPreferencesModel(
                    idServ = 1,
                    version = "1.00",
                    number = 123456,
                    password = "12345"
                )
            )
            val result = usecase("123456")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

}