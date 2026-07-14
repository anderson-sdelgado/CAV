package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IBasicCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IDeleteCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IInsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IRecoverDataCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IUpdateCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
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
        datasource = ICardSharedPreferencesDatasource(
            basicCardSharedPreferencesDatasource = mock(),
            insertCardSharedPreferencesDatasource = mock(),
            recoverDataCardSharedPreferencesDatasource = mock(),
            updateCardSharedPreferencesDatasource = mock(),
            deleteCardSharedPreferencesDatasource = mock(),
            sharedPreferences = sharedPreferences
        )
    }

    @Test
    fun `save - Check data correct in sharedPreferences`() =
        runTest {
            val data = CardSharedPreferencesModel(
                regAttendant = 18017,
                idCar = 1,
                local = LocalSharedPreferencesModel(address = "test")
            )
            val result = datasource.save(data)
            assertEquals(result.isSuccess, true)
            val resultGet = datasource.get()
            assertEquals(resultGet.isSuccess, true)
            val model = resultGet.getOrNull()!!
            assertEquals(model.regAttendant, 18017)
            assertEquals(model.idCar, 1)
            assertEquals(model.local!!.address, "test")
        }

    @Test
    fun `get - Check return empty model if sharedPreferences is empty`() =
        runTest {
            val result = datasource.get()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), CardSharedPreferencesModel())
        }

    @Test
    fun `has - Check return false if sharedPreferences is empty`() =
        runTest {
            val result = datasource.has()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), false)
        }

    @Test
    fun `has - Check return true if sharedPreferences is not empty`() =
        runTest {
            datasource.save(CardSharedPreferencesModel(regAttendant = 123L))
            val result = datasource.has()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), true)
        }

    @Test
    fun `clean - Check data is removed from sharedPreferences`() =
        runTest {
            datasource.save(CardSharedPreferencesModel(regAttendant = 18017))
            val resultHasBefore = datasource.has()
            assertEquals(resultHasBefore.getOrNull(), true)

            val resultClean = datasource.clean()
            assertEquals(resultClean.isSuccess, true)

            val resultHasAfter = datasource.has()
            assertEquals(resultHasAfter.getOrNull(), false)
        }

}
