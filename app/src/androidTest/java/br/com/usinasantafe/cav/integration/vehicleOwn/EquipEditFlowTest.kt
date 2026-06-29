package br.com.usinasantafe.cav.integration.vehicleOwn

import android.Manifest
import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.rule.GrantPermissionRule
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.presenter.view.card.detail.TAG_DETAIL_TEXT_FIELD
import br.com.usinasantafe.cav.presenter.view.card.equip.data.TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.equip.data.TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_VEHICLE_OWN_FULL_EDIT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.menu.TAG_VEHICLE_OWN_FULL_INSERT_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.state.TAG_INJURED_RADIO_BUTTON
import br.com.usinasantafe.cav.presenter.view.card.vehicleFull.TAG_VEHICLE_OWN_EDIT_BUTTON
import br.com.usinasantafe.cav.utils.dataLocalList
import br.com.usinasantafe.cav.utils.itemDataLocalList
import br.com.usinasantafe.cav.utils.natureList
import br.com.usinasantafe.cav.utils.optionDataLocalList
import br.com.usinasantafe.cav.utils.supportTeamsList
import br.com.usinasantafe.cav.utils.typeAccidentList
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
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class EquipEditFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
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

    @Inject
    lateinit var supportTeamsDao: SupportTeamsDao

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

            composeTestRule.onNodeWithTag("button_1")
                .performClick()
            composeTestRule.onNodeWithTag("button_9")
                .performClick()
            composeTestRule.onNodeWithTag("button_7")
                .performClick()
            composeTestRule.onNodeWithTag("button_5")
                .performClick()
            composeTestRule.onNodeWithTag("button_9")
                .performClick()
            composeTestRule.onNodeWithTag("button_OK")
                .performClick()

            Log.d("TestDebug", "Position 3")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("button_2")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_OK")
                .performClick()

            Log.d("TestDebug", "Position 4")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("AVANÇAR")
                .performClick()

            Log.d("TestDebug", "Position 5")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("AVANÇAR")
                .performClick()

            Log.d("TestDebug", "Position 6")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_VEHICLE_OWN_FULL_INSERT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 7")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("button_2")
                .performClick()
            composeTestRule.onNodeWithTag("button_2")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_OK")
                .performClick()

            Log.d("TestDebug", "Position 8")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
                .performTextInput("BATEU A FRENTE")
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 9")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("button_1")
                .performClick()
            composeTestRule.onNodeWithTag("button_2")
                .performClick()
            composeTestRule.onNodeWithTag("button_3")
                .performClick()
            composeTestRule.onNodeWithTag("button_4")
                .performClick()
            composeTestRule.onNodeWithTag("button_5")
                .performClick()
            composeTestRule.onNodeWithTag("button_6")
                .performClick()
            composeTestRule.onNodeWithTag("button_OK")
                .performClick()

            Log.d("TestDebug", "Position 10")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_INJURED_RADIO_BUTTON)
                .performClick()
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 11")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
                .performTextInput("MACHUCOU O BRAÇO")
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 12")

            //////////////////////////////////////////////////////////////////////////////////////

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("RETORNAR")
                .performClick()

            Log.d("TestDebug", "Position 13")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("${TAG_VEHICLE_OWN_FULL_EDIT_BUTTON}1")
                .performClick()

            Log.d("TestDebug", "Position 14")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_VEHICLE_OWN_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 15")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("RETORNAR")
                .performClick()

            Log.d("TestDebug", "Position 16")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_VEHICLE_OWN_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 17")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 18")

            composeTestRule.waitUntilTimeout(3_000)

            scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

            Log.d("TestDebug", "Position 19")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 20")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag("button_CLEAN")
                .performClick()
            composeTestRule.onNodeWithTag("button_CLEAN")
                .performClick()
            composeTestRule.onNodeWithTag("button_CLEAN")
                .performClick()
            composeTestRule.onNodeWithTag("button_CLEAN")
                .performClick()
            composeTestRule.onNodeWithTag("button_2")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_0")
                .performClick()
            composeTestRule.onNodeWithTag("button_OK")
                .performClick()

            Log.d("TestDebug", "Position 21")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 22")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("RETORNAR")
                .performClick()

            Log.d("TestDebug", "Position 23")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON)
                .performClick()

            Log.d("TestDebug", "Position 24")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
                .performTextInput("TESTE")
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 26")

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("RETORNAR")
                .performClick()

            Log.d("TestDebug", "Position 25")

//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_VEHICLE_SEC_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 37")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 38")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_VEHICLE_SEC_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 39")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 40")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            scenario.onActivity { activity ->
//                activity.onBackPressedDispatcher.onBackPressed()
//            }
//
//            Log.d("TestDebug", "Position 41")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 42")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_2")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_5")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 43")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 44")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 45")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("DETALHE EQUIP SEC")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 46")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 47")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 48")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 49")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 50")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            scenario.onActivity { activity ->
//                activity.onBackPressedDispatcher.onBackPressed()
//            }
//
//            Log.d("TestDebug", "Position 51")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_EQUIP_DATA_EQUIP_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 52")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_3")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 53")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 54")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 55")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_EQUIP_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 56")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 57")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 58")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 59")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DRIVER_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 60")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 61")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DRIVER_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 62")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_COLAB_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 63")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            scenario.onActivity { activity ->
//                activity.onBackPressedDispatcher.onBackPressed()
//            }
//
//            Log.d("TestDebug", "Position 64")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_COLAB_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 65")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 66")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_STATE_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 67")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 68")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_STATE_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 69")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("ILESO")
//                .performClick()
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 70")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 71")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 72")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 73")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE EDITAR ")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 74")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 75")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_PASSENGERS_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 76")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 77")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_PASSENGERS_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 78")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 79")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            scenario.onActivity { activity ->
//                activity.onBackPressedDispatcher.onBackPressed()
//            }
//
//            Log.d("TestDebug", "Position 80")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 81")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_2")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_3")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_4")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_5")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_6")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 82")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 83")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 84")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("MORTO")
//                .performClick()
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 85")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 84")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 86")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE INSERIR PASSANGEIRO")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 87")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 88")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 89")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 90")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_COLAB_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 91")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            scenario.onActivity { activity ->
//                activity.onBackPressedDispatcher.onBackPressed()
//            }
//
//            Log.d("TestDebug", "Position 91")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_COLAB_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 92")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_CLEAN")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 94")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_STATE_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 95")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 96")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_STATE_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 97")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("ILESO")
//                .performClick()
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 98")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 99")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 100")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_DATA_COLAB_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 102")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE EDITAR ")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 103")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 104")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 105")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_9")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_7")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_5")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_9")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 106")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("ILESO")
//                .performClick()
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 107")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE EDITAR ")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 108")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 109")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_PASSENGERS_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 110")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 111")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("EXCLUIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 112")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("SIM")
//                .performClick()
//
//            Log.d("TestDebug", "Position 113")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 114")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_VEHICLE_SEC_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 115")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("INSERIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 116")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_2")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 117")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE EXCLUSÃO")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 118")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 119")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_VEHICLE_SEC_OWN_EDIT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 120")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("tag_item_edit_1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 121")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("EXCLUIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 122")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("SIM")
//                .performClick()
//
//            Log.d("TestDebug", "Position 123")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 124")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 124")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_VEHICLE_OWN_FULL_INSERT_BUTTON)
//                .performClick()
//
//            Log.d("TestDebug", "Position 125")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_2")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_2")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 126")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE DE EXCLUSÃO")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 127")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_8")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_0")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_1")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_7")
//                .performClick()
//            composeTestRule.onNodeWithTag("button_OK")
//                .performClick()
//
//            Log.d("TestDebug", "Position 128")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_INJURED_RADIO_BUTTON)
//                .performClick()
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 129")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag(TAG_DETAIL_TEXT_FIELD)
//                .performTextInput("TESTE DE EXCLUSÃO")
//            composeTestRule.onNodeWithText("SALVAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 130")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("RETORNAR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 131")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithTag("${TAG_VEHICLE_OWN_FULL_EDIT_BUTTON}1")
//                .performClick()
//
//            Log.d("TestDebug", "Position 132")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("EXCLUIR")
//                .performClick()
//
//            Log.d("TestDebug", "Position 133")
//
//            composeTestRule.waitUntilTimeout(3_000)
//
//            composeTestRule.onNodeWithText("SIM")
//                .performClick()
//
//            Log.d("TestDebug", "Position 134")

            composeTestRule.waitUntilTimeout(20_000)

        }

    private suspend fun initialRegister() {

        cardSharedPreferencesDatasource.clean()

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
                ),
                ColabRoomModel(
                    reg = 123456,
                    name = "JOAO HENRIQUE GUSTAVO"
                ),
                ColabRoomModel(
                    reg = 111000,
                    name = "TESTE NOVO REGISTRO"
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
                ),
                EquipRoomModel(
                    id = 20,
                    nro = 2200,
                    description = "CAMINHAO"
                ),
                EquipRoomModel(
                    id = 30,
                    nro = 2000,
                    description = "CAMINHAO 2"
                ),
                EquipRoomModel(
                    id = 35,
                    nro = 2500,
                    description = "CAMINHAO 3"
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

        val supportTeamsType = object : TypeToken<List<SupportTeamsRoomModel>>() {}.type
        val supportTeamsList = gson.fromJson<List<SupportTeamsRoomModel>>(supportTeamsList, supportTeamsType)
        supportTeamsDao.insertAll(supportTeamsList)

    }

}