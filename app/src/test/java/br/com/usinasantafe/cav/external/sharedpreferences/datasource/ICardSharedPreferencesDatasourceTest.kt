package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
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
class ICardSharedPreferencesDatasourceTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var datasource: ICardSharedPreferencesDatasource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        datasource = ICardSharedPreferencesDatasource(sharedPreferences)
    }

    @Test
    fun `setRegAttendant - Check alter data correct the Config SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                regAttendant = 18017
            )
            datasource.save(data)
            val resultBefore = datasource.get()
            assertEquals(
                resultBefore.isSuccess,
                true
            )
            val modelBefore = resultBefore.getOrNull()!!
            assertEquals(
                modelBefore.regAttendant,
                18017
            )
            val result = datasource.setRegAttendant(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            val resultAfter = datasource.get()
            assertEquals(
                resultAfter.isSuccess,
                true
            )
            val modelAfter = resultAfter.getOrNull()!!
            assertEquals(
                modelAfter.regAttendant,
                19759
            )
        }

    @Test
    fun `setIdCar - Check alter data correct the Config SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idCar = 2
            )
            datasource.save(data)
            val resultBefore = datasource.get()
            assertEquals(
                resultBefore.isSuccess,
                true
            )
            val modelBefore = resultBefore.getOrNull()!!
            assertEquals(
                modelBefore.idCar,
                2
            )
            val result = datasource.setIdCar(10)
            assertEquals(
                result.isSuccess,
                true
            )
            val resultAfter = datasource.get()
            assertEquals(
                resultAfter.isSuccess,
                true
            )
            val modelAfter = resultAfter.getOrNull()!!
            assertEquals(
                modelAfter.idCar,
                10
            )
        }

    @Test
    fun `setLocal - Check alter data correct the Config SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                local = LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            datasource.save(data)
            val resultBefore = datasource.get()
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
            val result = datasource.setLocal(
                LocalSharedPreferencesModel(
                    address = "Test2",
                    latitude = 1.0,
                    longitude = 1.0
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val resultAfter = datasource.get()
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