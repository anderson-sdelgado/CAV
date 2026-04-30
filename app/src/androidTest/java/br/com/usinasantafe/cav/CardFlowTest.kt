package br.com.usinasantafe.cav

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.utils.waitUntilTimeout
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

    @Inject
    lateinit var configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource

    @Inject
    lateinit var colabDao: ColabDao

    @Inject
    lateinit var equipDao: EquipDao

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

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

            composeTestRule.waitUntilTimeout(20_000)

        }

    private suspend fun initialRegister() {

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
                )
            )
        )

        equipDao.insertAll(
            listOf(
                EquipRoomModel(
                    id = 2,
                    nro = 200,
                    desc = "AMBULANCIA"
                )
            )
        )

    }

}