package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IConfigSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: IConfigSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = IConfigSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `has - Check return false if not have data`() =
        runTest {
            val result = datasource.has()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `has - Check return true if have data`() =
        runTest {
            val data = ConfigSharedPreferencesModel(
                number = 1,
                password = "123456",
                idServ = 1,
                version = "1.00",
                statusSend = StatusSend.SENT
            )
            datasource.save(data)
            val result = datasource.has()
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
    fun `getPassword - Check return failure if field is null`() =
        runTest {
            val result = datasource.getPassword()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IConfigSharedPreferencesDatasource.getPassword"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: password is required"
            )
        }

    @Test
    fun `getPassword - Check return correct if function execute successfully`() =
        runTest {
            val data = ConfigSharedPreferencesModel(
                number = 1,
                password = "123456",
                idServ = 1,
                version = "1.00",
                statusSend = StatusSend.SENT
            )
            datasource.save(data)
            val result = datasource.getPassword()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123456"
            )
        }

    @Test
    fun `get - Check return ConfigSharedPreferencesModel() if not have data`() =
        runTest {
            val result = datasource.get()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ConfigSharedPreferencesModel()
            )
        }

    @Test
    fun `get - Check return failure if number is null`() =
        runTest {
            val data = ConfigSharedPreferencesModel()
            datasource.save(data)
            val result = datasource.get()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IConfigSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: number is required"
            )
        }

    @Test
    fun `get - Check return failure if password is null`() =
        runTest {
            val data = ConfigSharedPreferencesModel(
                number = 16997417840
            )
            datasource.save(data)
            val result = datasource.get()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IConfigSharedPreferencesDatasource.get"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: password is required"
            )
        }

    @Test
    fun `get - Check return correct if function execute successfully`() =
        runTest {
            val data = ConfigSharedPreferencesModel(
                number = 16997417840,
                password = "12345"
            )
            datasource.save(data)
            val result = datasource.get()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ConfigSharedPreferencesModel(
                    number = 16997417840,
                    password = "12345"
                )
            )
        }
}