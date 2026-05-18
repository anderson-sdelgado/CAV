package br.com.usinasantafe.cav

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.rule.GrantPermissionRule
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_ATTENDANT_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_CAR_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_LOCAL_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_NATURE_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_TYPE_ACCIDENT_EDIT_BUTTON
import br.com.usinasantafe.cav.utils.typeAccidentList
import br.com.usinasantafe.cav.utils.dataLocalList
import br.com.usinasantafe.cav.utils.itemDataLocalList
import br.com.usinasantafe.cav.utils.natureList
import br.com.usinasantafe.cav.utils.optionDataLocalList
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class CardFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @Inject
    lateinit var configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource

    @Inject
    lateinit var colabDao: ColabDao

    @Inject
    lateinit var equipDao: EquipDao

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var natureDao: NatureDao

    @Inject
    lateinit var typeAccidentDao: TypeAccidentDao

    @Inject
    lateinit var dataLocalDao: DataLocalDao

    @Inject
    lateinit var optionDataLocalDao: OptionDataLocalDao

    @Inject
    lateinit var itemDataLocalDao: ItemDataLocalDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun flow() =
        runTest(
            timeout = 10.minutes
        ) {

            initialRegister()

            val scenario = ActivityScenario.launch(MainActivity::class.java)

            Log.d("TestDebug", "Position 1")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("APONTAMENTO")
                .assertIsDisplayed()
            composeTestRule.onNodeWithText("APONTAMENTO")
                .performClick()

            Log.d("TestDebug", "Position 2")

            composeTestRule.waitUntilTimeout(3_000)

            scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

            Log.d("TestDebug", "Position 3")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("APONTAMENTO")
                .performClick()

            Log.d("TestDebug", "Position 4")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("9")
                .performClick()
            composeTestRule.onNodeWithText("7")
                .performClick()
            composeTestRule.onNodeWithText("5")
                .performClick()
            composeTestRule.onNodeWithText("9")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 5")

            composeTestRule.waitUntilTimeout(3_000)


            val resultCardAttendant = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultCardAttendant.isSuccess,
                true
            )
            val modelCardAttendant = resultCardAttendant.getOrNull()!!
            assertEquals(
                modelCardAttendant.regAttendant,
                19759
            )
            assertEquals(
                modelCardAttendant.idCar,
                null
            )

            Log.d("TestDebug", "Position 6")

            composeTestRule.waitUntilTimeout(3_000)

            scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

            Log.d("TestDebug", "Position 7")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("9")
                .performClick()
            composeTestRule.onNodeWithText("7")
                .performClick()
            composeTestRule.onNodeWithText("5")
                .performClick()
            composeTestRule.onNodeWithText("9")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 8")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("2")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 9")

            composeTestRule.waitUntilTimeout(3_000)

            val resultCardCar = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultCardCar.isSuccess,
                true
            )
            val modelCardCar = resultCardCar.getOrNull()!!
            assertEquals(
                modelCardCar.regAttendant,
                19759
            )
            assertEquals(
                modelCardCar.idCar,
                2
            )

            Log.d("TestDebug", "Position 10")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_ATTENDANT_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 11")

            composeTestRule.waitUntilTimeout(3_000)

            scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

            Log.d("TestDebug", "Position 12")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_ATTENDANT_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 13")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("8")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("7")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 14")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_CAR_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 15")

            composeTestRule.waitUntilTimeout(3_000)

            scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

            Log.d("TestDebug", "Position 16")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_CAR_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 15")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("3")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 16")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_NATURE_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 17")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 18")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_NATURE_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 19")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("item_check_box_1")
                .performClick()
            composeTestRule.onNodeWithTag("item_check_box_4")
                .performClick()
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 20")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("CANCELAR")
                .performClick()

            Log.d("TestDebug", "Position 21")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("SIM")
                .performClick()

            Log.d("TestDebug", "Position 22")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("APONTAMENTO")
                .performClick()

            Log.d("TestDebug", "Position 23")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("8")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("1")
                .performClick()
            composeTestRule.onNodeWithText("7")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 24")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("3")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("0")
                .performClick()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 25")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_NATURE_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 26")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("item_check_box_2")
                .performClick()
            composeTestRule.onNodeWithTag("item_check_box_5")
                .performClick()
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 27")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_TYPE_ACCIDENT_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 28")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 29")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_TYPE_ACCIDENT_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 30")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("item_check_box_1")
                .performClick()
            composeTestRule.onNodeWithTag("item_check_box_4")
                .performClick()
            composeTestRule.onNodeWithTag("item_check_box_10")
                .performClick()
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 31")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("AVANÇAR")
                .performClick()

            Log.d("TestDebug", "Position 32")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_LOCAL_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 33")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("LOCALIZAÇÃO ATUAL")
                .performClick()

            Log.d("TestDebug", "Position 34")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("CAPTURAR LOCALIZAÇÃO")
                .performClick()

            Log.d("TestDebug", "Position 35")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("SIM")
                .performClick()

            Log.d("TestDebug", "Position 36")

            composeTestRule.waitUntilTimeout(20_000)

        }

    private suspend fun initialRegister() {

        cardSharedPreferencesDatasource.clear()

        configSharedPreferencesDatasource.save(
            ConfigSharedPreferencesModel(
                number = 16997417840,
                password = "12345",
                idServ = 1,
                version = "1.00",
                flagUpdate = true,
                statusSend = StatusSend.SENT
            )
        )

        colabDao.insertAll(
            listOf(
                ColabRoomModel(
                    reg = 19759,
                    name = "ANDERSON"
                ),
                ColabRoomModel(
                    reg = 18017,
                    name = "RONALDO"
                )
            )
        )

        equipDao.insertAll(
            listOf(
                EquipRoomModel(
                    id = 2,
                    nro = 200,
                    description = "AMBULANCIA"
                ),
                EquipRoomModel(
                    id = 10,
                    nro = 300,
                    description = "GOL"
                )
            )
        )

        val gson = Gson()

        val natureType = object : TypeToken<List<NatureRoomModel>>() {}.type
        val natureList = gson.fromJson<List<NatureRoomModel>>(natureList, natureType)
        natureDao.insertAll(natureList)

        val typeAccidentType = object : TypeToken<List<TypeAccidentRoomModel>>() {}.type
        val typeAccidentList = gson.fromJson<List<TypeAccidentRoomModel>>(typeAccidentList, typeAccidentType)
        typeAccidentDao.insertAll(typeAccidentList)

        val dataLocalType = object : TypeToken<List<DataLocalRoomModel>>() {}.type
        val dataLocalList = gson.fromJson<List<DataLocalRoomModel>>(dataLocalList, dataLocalType)
        dataLocalDao.insertAll(dataLocalList)

        val optionDataLocalType = object : TypeToken<List<OptionDataLocalRoomModel>>() {}.type
        val optionDataLocalList = gson.fromJson<List<OptionDataLocalRoomModel>>(optionDataLocalList, optionDataLocalType)
        optionDataLocalDao.insertAll(optionDataLocalList)

        val itemDataLocalType = object : TypeToken<List<ItemDataLocalRoomModel>>() {}.type
        val itemDataLocalList = gson.fromJson<List<ItemDataLocalRoomModel>>(itemDataLocalList, itemDataLocalType)
        itemDataLocalDao.insertAll(itemDataLocalList)

    }

}