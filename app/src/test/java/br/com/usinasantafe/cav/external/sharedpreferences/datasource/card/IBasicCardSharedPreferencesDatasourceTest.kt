package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Provider
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IBasicCardSharedPreferencesDatasourceTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cardDatasource: CardSharedPreferencesDatasource
    private lateinit var datasource: IBasicCardSharedPreferencesDatasource

    class TestProvider<T : Any> : Provider<T> {
        lateinit var value: T
        override fun get(): T = value
    }

    @Before
    fun setup() {

        context = ApplicationProvider.getApplicationContext()

        sharedPreferences =
            context.getSharedPreferences("test", Context.MODE_PRIVATE)

        sharedPreferences.edit().clear().commit()

        val provider = TestProvider<CardSharedPreferencesDatasource>()

        datasource =
            IBasicCardSharedPreferencesDatasource(provider)

        cardDatasource =
            ICardSharedPreferencesDatasource(
                basicCardSharedPreferencesDatasource = datasource,
                insertCardSharedPreferencesDatasource = mock(),
                recoverDataCardSharedPreferencesDatasource = mock(),
                updateCardSharedPreferencesDatasource = mock(),
                deleteCardSharedPreferencesDatasource = mock(),
                sharedPreferences = sharedPreferences
            )

        provider.value = cardDatasource

    }

    @Test
    fun `setRegAttendant - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                regAttendant = 18017
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.regAttendant,
                18017
            )
            val result =  datasource.setRegAttendant(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.regAttendant,
                19759
            )
        }

    @Test
    fun `setIdCar - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idCar = 2
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.idCar,
                2
            )
            val result = datasource.setIdCar(10)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.idCar,
                10
            )
        }

    @Test
    fun `setLocal - Check alter data correct in sharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                local = LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.local.address,
                "Test"
            )
            assertEquals(
                modelBefore.local.latitude,
                0.0
            )
            assertEquals(
                modelBefore.local.longitude,
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
            val modelAfter =
                cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.local.address,
                "Test2"
            )
            assertEquals(
                modelAfter.local.latitude,
                1.0
            )
            assertEquals(
                modelAfter.local.longitude,
                1.0
            )
        }

    @Test
    fun `listIdNature - Check return data and sharePreferences is empty`() =
        runTest {
            val result = datasource.listIdNature()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                emptyList()
            )
        }

    @Test
    fun `listIdNature - Check return data and sharePreferences with data`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idNatureList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val result = datasource.listIdNature()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdNatureList - Check alter data correct in SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idNatureList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            val listBefore = modelBefore.idNatureList
            assertEquals(
                listBefore,
                listOf(1, 2)
            )
            val result = datasource.setIdNatureList(listOf(3, 4))
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            val listAfter= modelAfter.idNatureList
            assertEquals(
                listAfter,
                listOf(3, 4)
            )
        }

    @Test
    fun `getRegAttendant - Check return failure if field is null`() =
        runTest {
            val result = datasource.getRegAttendant()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardSharedPreferencesDatasource.getRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: regAttendant is required"
            )
        }

    @Test
    fun `getRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            val data = CardSharedPreferencesModel(
                regAttendant = 19759
            )
            cardDatasource.save(data)
            val result = datasource.getRegAttendant()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                19759
            )
        }

    @Test
    fun `getIdCar - Check return failure if field is null`() =
        runTest {
            val result = datasource.getIdCar()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardSharedPreferencesDatasource.getIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: idCar is required"
            )
        }

    @Test
    fun `getIdCar - Check return correct if function execute successfully`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idCar = 1
            )
            cardDatasource.save(data)
            val result = datasource.getIdCar()
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
    fun `listIdTypeAccident - Check return data and sharePreferences is empty`() =
        runTest {
            val result = datasource.listIdTypeAccident()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                emptyList()
            )
        }

    @Test
    fun `listIdTypeAccident - Check return data and sharePreferences with data`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idTypeAccidentList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val result = datasource.listIdTypeAccident()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdTypeAccidentList - Check alter data correct in SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idTypeAccidentList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            val listBefore = modelBefore.idTypeAccidentList
            assertEquals(
                listBefore,
                listOf(1, 2)
            )
            val result = datasource.setIdTypeAccidentList(listOf(3, 4))
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            val listAfter= modelAfter.idTypeAccidentList
            assertEquals(
                listAfter,
                listOf(3, 4)
            )
        }

    @Test
    fun `getLocal- Check return failure if model is empty`() =
        runTest {
            val result = datasource.getLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                LocalSharedPreferencesModel()
            )
        }

    @Test
    fun `getLocal - Check return correct if function execute successfully`() =
        runTest {
            val data = CardSharedPreferencesModel(
                local = LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 25.356,
                    longitude = 27.96352
                )
            )
            cardDatasource.save(data)
            val result = datasource.getLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 25.356,
                    longitude = 27.96352
                )
            )
        }

    @Test
    fun `listIdDataLocal - Check return data and sharePreferences is empty`() =
        runTest {
            val result = datasource.listIdDataLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                emptyList()
            )
        }

    @Test
    fun `listIdDataLocal - Check return data and sharePreferences with data`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idDataLocalList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val result = datasource.listIdDataLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdDataLocalList - Check alter data correct in SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idDataLocalList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            val listBefore = modelBefore.idDataLocalList
            assertEquals(
                listBefore,
                listOf(1, 2)
            )
            val result = datasource.setIdDataLocalList(listOf(3, 4))
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            val listAfter= modelAfter.idDataLocalList
            assertEquals(
                listAfter,
                listOf(3, 4)
            )
        }

    @Test
    fun `listIdSupportTeams - Check return data and sharePreferences is empty`() =
        runTest {
            val result = datasource.listIdSupportTeams()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                emptyList()
            )
        }

    @Test
    fun `listIdSupportTeams - Check return data and sharePreferences with data`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idSupportTeamsList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val result = datasource.listIdSupportTeams()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdSupportTeamsList - Check alter data correct in SharedPreferences internal`() =
        runTest {
            val data = CardSharedPreferencesModel(
                idSupportTeamsList = listOf(1, 2)
            )
            cardDatasource.save(data)
            val modelBefore =
                cardDatasource.get().getOrThrow()
            val listBefore = modelBefore.idSupportTeamsList
            assertEquals(
                listBefore,
                listOf(1, 2)
            )
            val result = datasource.setIdSupportTeamsList(listOf(3, 4))
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter =
                cardDatasource.get().getOrThrow()
            val listAfter= modelAfter.idSupportTeamsList
            assertEquals(
                listAfter,
                listOf(3, 4)
            )
        }

}
