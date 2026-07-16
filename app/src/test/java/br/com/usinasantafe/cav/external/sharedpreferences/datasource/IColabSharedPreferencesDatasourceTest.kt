package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.getOrThrow
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IColabSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: IColabSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = IColabSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `setRegColab - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                reg = 123456,
                state = State.UNHARMED,
                detail = "Test"
            )
            datasource.save(data)
            val modelBefore =
                datasource.get().getOrThrow()
            assertEquals(
                modelBefore.reg,
                123456
            )
            assertEquals(
                modelBefore.state,
                State.UNHARMED
            )
            assertEquals(
                modelBefore.detail,
                "Test"
            )
            val result =  datasource.setRegColab(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                datasource.get().getOrThrow()
            assertEquals(
                modelAfter.reg,
                19759
            )
            assertEquals(
                modelAfter.state,
                null
            )
            assertEquals(
                modelAfter.detail,
                null
            )
        }

    @Test
    fun `setState - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                state = State.DEAD
            )
            datasource.save(data)
            val modelBefore =
                datasource.get().getOrThrow()
            assertEquals(
                modelBefore.state,
                State.DEAD
            )
            val result =  datasource.setState(State.UNHARMED)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                datasource.get().getOrThrow()
            assertEquals(
                modelAfter.state,
                State.UNHARMED
            )
        }

    @Test
    fun `setDetail - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                detail = "Test"
            )
            datasource.save(data)
            val modelBefore =
                datasource.get().getOrThrow()
            assertEquals(
                modelBefore.detail,
                "Test"
            )
            val result =  datasource.setDetail("Test2")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                datasource.get().getOrThrow()
            assertEquals(
                modelAfter.detail,
                "Test2"
            )
        }

    @Test
    fun `getRegColab - Check return correct if field is null`() =
        runTest {
            val result = datasource.getRegColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getRegColab - Check return correct if function execute successfully`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                reg = 123456,
                state = State.UNHARMED,
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getRegColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                123456
            )
        }

    @Test
    fun `getState - Check return correct if field is null`() =
        runTest {
            val result = datasource.getState()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getState - Check return correct if function execute successfully`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                reg = 123456,
                state = State.UNHARMED,
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getState()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getDetail - Check return correct if function execute successfully and return null`() =
        runTest {
            val result = datasource.getDetail()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetail - Check return correct if function execute successfully`() =
        runTest {
            val data = ColabCardSharedPreferencesModel(
                reg = 123456,
                state = State.UNHARMED,
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getDetail()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "Test"
            )
        }

}