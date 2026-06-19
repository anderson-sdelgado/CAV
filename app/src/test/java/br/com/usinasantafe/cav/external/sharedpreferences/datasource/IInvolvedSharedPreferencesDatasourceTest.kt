package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IInvolvedSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: IInvolvedSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = IInvolvedSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `setDocument - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.document,
                "123.456.789-09"
            )
            assertEquals(
                modelBefore.name,
                "Name"
            )
            assertEquals(
                modelBefore.phone,
                "(16) 99999-9999"
            )
            assertEquals(
                modelBefore.state,
                State.UNHARMED
            )
            assertEquals(
                modelBefore.detail,
                "Detail"
            )
            val result = datasource.setDocument("456.789.123-99")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.document,
                "456.789.123-99"
            )
            assertEquals(
                modelAfter.name,
                null
            )
            assertEquals(
                modelAfter.phone,
                null
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
    fun `setName - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                name = "Name",
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.name,
                "Name"
            )
            val result =  datasource.setName("Name2")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.name,
                "Name2"
            )
        }

    @Test
    fun `setPhone - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                phone = "(16) 99999-9999",
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.phone,
                "(16) 99999-9999"
            )
            val result =  datasource.setPhone("(16) 88888-8888")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.phone,
                "(16) 88888-8888"
            )
        }

    @Test
    fun `setState - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                state = State.UNHARMED,
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.state,
                State.UNHARMED
            )
            val result =  datasource.setState(State.INJURED)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.state,
                State.INJURED
            )
        }

    @Test
    fun `setDetail - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                detail = "Test"
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.detail,
                "Test"
            )
            val result =  datasource.setDetail("Test2")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.detail,
                "Test2"
            )
        }

    @Test
    fun `getDocument - Check return correct if field is null`() =
        runTest {
            val result = datasource.getDocument()
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
    fun `getDocument - Check return correct if function execute successfully`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val result = datasource.getDocument()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "123.456.789-09"
            )
        }

    @Test
    fun `getName - Check return correct if field is null`() =
        runTest {
            val result = datasource.getName()
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
    fun `getName - Check return correct if function execute successfully`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val result = datasource.getName()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "Name"
            )
        }

    @Test
    fun `getPhone - Check return correct if field is null`() =
        runTest {
            val result = datasource.getPhone()
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
    fun `getPhone - Check return correct if function execute successfully`() =
        runTest {
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val result = datasource.getPhone()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "(16) 99999-9999"
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
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val result = datasource.getState()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                State.UNHARMED
            )
        }

    @Test
    fun `getDetail - Check return correct if field is null`() =
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
            val data = InvolvedSharedPreferencesModel(
                document = "123.456.789-09",
                name = "Name",
                phone = "(16) 99999-9999",
                state = State.UNHARMED,
                detail = "Detail"
            )
            datasource.save(data)
            val result = datasource.getDetail()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "Detail"
            )
        }

}