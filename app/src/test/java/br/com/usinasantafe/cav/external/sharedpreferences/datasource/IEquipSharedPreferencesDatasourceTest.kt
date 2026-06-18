package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
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
class IEquipSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: IEquipSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = IEquipSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `setIdEquip - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = EquipSharedPreferencesModel(
                idEquip = 1,
                detail = "Test"
            )
            datasource.save(data)
            val modelBefore =
                datasource.get().getOrThrow()
            assertEquals(
                modelBefore.idEquip,
                1
            )
            assertEquals(
                modelBefore.detail,
                "Test"
            )
            val result =  datasource.setIdEquip(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                datasource.get().getOrThrow()
            assertEquals(
                modelAfter.idEquip,
                2
            )
            assertEquals(
                modelAfter.detail,
                null
            )
        }

    @Test
    fun `setDetail - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = EquipSharedPreferencesModel(
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
    fun `getIdEquip - Check return failure if field is null`() =
        runTest {
            val result = datasource.getIdEquip()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IEquipSharedPreferencesDatasource.getIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: idEquip is required"
            )
        }

    @Test
    fun `getIdEquip - Check return correct if function execute successfully`() =
        runTest {

            val data = EquipSharedPreferencesModel(
                idEquip = 1,
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getIdEquip()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                1
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
            val data = EquipSharedPreferencesModel(
                idEquip = 1,
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