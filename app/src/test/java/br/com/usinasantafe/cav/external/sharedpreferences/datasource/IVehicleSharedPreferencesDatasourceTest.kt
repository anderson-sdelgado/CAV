package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
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
class IVehicleSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: IVehicleSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = IVehicleSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `setPlate - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = VehicleSharedPreferencesModel(
                plate = "ABC1234",
                brand = "GOL",
                detail = "Test"
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.plate,
                "ABC1234"
            )
            assertEquals(
                modelBefore.brand,
                "GOL"
            )
            assertEquals(
                modelBefore.detail,
                "Test"
            )
            val result = datasource.setPlate("CBA4321")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.plate,
                "CBA4321"
            )
            assertEquals(
                modelAfter.brand,
                null
            )
            assertEquals(
                modelAfter.detail,
                null
            )
        }

    @Test
    fun `setBrand - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = VehicleSharedPreferencesModel(
                brand = "GOL"
            )
            datasource.save(data)
            val modelBefore = datasource.get().getOrThrow()
            assertEquals(
                modelBefore.brand,
                "GOL"
            )
            val result =  datasource.setBrand("UNO")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = datasource.get().getOrThrow()
            assertEquals(
                modelAfter.brand,
                "UNO"
            )
        }

    @Test
    fun `setDetail - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = VehicleSharedPreferencesModel(
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
    fun `getPlate - Check return correct if field is null`() =
        runTest {
            val result = datasource.getPlate()
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
    fun `getPlate - Check return correct if function execute successfully`() =
        runTest {
            val data = VehicleSharedPreferencesModel(
                plate = "ABC1234",
                brand = "GOL",
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getPlate()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "ABC1234"
            )
        }

    @Test
    fun `getBrand - Check return correct if field is null`() =
        runTest {
            val result = datasource.getBrand()
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
    fun `getBrand - Check return correct if function execute successfully`() =
        runTest {
            val data = VehicleSharedPreferencesModel(
                plate = "ABC1234",
                brand = "GOL",
                detail = "Test"
            )
            datasource.save(data)
            val result = datasource.getBrand()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "GOL"
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
            val data = VehicleSharedPreferencesModel(
                plate = "ABC1234",
                brand = "GOL",
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