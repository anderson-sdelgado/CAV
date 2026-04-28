package br.com.usinasantafe.cav

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import br.com.usinasantafe.cav.di.provider.BaseUrlModuleTest
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.WEB_ALL_COLAB
import br.com.usinasantafe.cav.lib.WEB_ALL_EQUIP
import br.com.usinasantafe.cav.lib.WEB_SAVE_TOKEN
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.presenter.theme.TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE
import br.com.usinasantafe.cav.presenter.view.configuration.config.TAG_NUMBER_TEXT_FIELD_CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.view.configuration.config.TAG_PASSWORD_TEXT_FIELD_CONFIG_SCREEN
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.collections.get
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class ConfigFlowTest {

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

    companion object {

        private lateinit var mockWebServer: MockWebServer

        private val resultToken = """
            {
                "idServ": 16
            }
        """.trimIndent()

        private val resultColab = """
            [
                {"reg":19759,"name":"ANDERSON DA SILVA DELGADO"}
            ]
        """.trimIndent()

        private val resultEquip = """
            [
              {"id":1,"nro":100,"desc":"Class1"},
              {"id":2,"nro":200,"desc":"Class2"}
            ]
        """.trimIndent()

        @BeforeClass
        @JvmStatic
        fun setupClass() {

            val dispatcherSuccess: Dispatcher = object : Dispatcher() {
                @Throws(InterruptedException::class)
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        "/$WEB_SAVE_TOKEN" -> MockResponse().setBody(resultToken)
                        "/$WEB_ALL_COLAB" -> MockResponse().setBody(resultColab)
                        "/$WEB_ALL_EQUIP" -> MockResponse().setBody(resultEquip)
                        else -> MockResponse().setResponseCode(404)
                    }
                }
            }

            mockWebServer = MockWebServer()
            mockWebServer.dispatcher = dispatcherSuccess
            mockWebServer.start()

            BaseUrlModuleTest.url = mockWebServer.url("/").toString()
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            mockWebServer.shutdown()
        }

    }

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun flow() =
        runTest(
            timeout = 10.minutes
        ) {

            Log.d("TestDebug", "Position 1")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("APONTAMENTO").assertIsDisplayed()
            composeTestRule.onNodeWithText("CONFIGURAÇÃO").assertIsDisplayed()
            composeTestRule.onNodeWithText("SAIR").assertIsDisplayed()

            Log.d("TestDebug", "Position 2")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("APONTAMENTO")
                .performClick()

            Log.d("TestDebug", "Position 3")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithTag(TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE)
                .performClick()

            Log.d("TestDebug", "Position 4")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("CONFIGURAÇÃO")
                .performClick()

            Log.d("TestDebug", "Position 5")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("SENHA").assertIsDisplayed()

            Log.d("TestDebug", "Position 6")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("CANCELAR")
                .performClick()

            Log.d("TestDebug", "Position 7")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("CONFIGURAÇÃO")
                .performClick()

            Log.d("TestDebug", "Position 8")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("SENHA").assertIsDisplayed()
            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 9")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 10")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithTag(TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE)
                .performClick()

            Log.d("TestDebug", "Position 11")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithTag(TAG_NUMBER_TEXT_FIELD_CONFIG_SCREEN)
                .performTextInput("16997417840")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 12")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithTag(TAG_BUTTON_OK_ALERT_DIALOG_SIMPLE)
                .performClick()

            Log.d("TestDebug", "Position 13")

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithTag(TAG_PASSWORD_TEXT_FIELD_CONFIG_SCREEN)
                .performTextInput("12345")
            composeTestRule.onNodeWithText("SALVAR")
                .performClick()

            Log.d("TestDebug", "Position 14")

            composeTestRule.waitUntilTimeout(10_000)

            asserts()

            composeTestRule.waitUntilTimeout()

            composeTestRule.onNodeWithText("OK")
                .performClick()

            Log.d("TestDebug", "Position 15")

            composeTestRule.waitUntilTimeout(10_000)

        }


    private suspend fun asserts() {

        val resultGetConfig = configSharedPreferencesDatasource.get()
        assertEquals(
            resultGetConfig.isSuccess,
            true
        )
        val config = resultGetConfig.getOrNull()!!
        assertEquals(
            config.idServ,
            16
        )
        assertEquals(
            config.number,
            16997417840
        )
        assertEquals(
            config.password,
            "12345"
        )
        assertEquals(
            config.version,
            "1.0"
        )

        val colabRoomModelList = colabDao.all()
        assertEquals(
            colabRoomModelList.size,
            1
        )
        val colabRoomModel1 = colabRoomModelList[0]
        assertEquals(
            colabRoomModel1.reg,
            19759
        )
        assertEquals(
            colabRoomModel1.name,
            "ANDERSON DA SILVA DELGADO"
        )

        val equipRoomModelList = equipDao.all()
        assertEquals(
            equipRoomModelList.size,
            2
        )
        val equipRoomModel1 = equipRoomModelList[0]
        assertEquals(
            equipRoomModel1.id,
            1
        )
        assertEquals(
            equipRoomModel1.nro,
            100
        )
        assertEquals(
            equipRoomModel1.desc,
            "Class1"
        )
        val equipRoomModel2 = equipRoomModelList[1]
        assertEquals(
            equipRoomModel2.id,
            2
        )
        assertEquals(
            equipRoomModel2.nro,
            200
        )
        assertEquals(
            equipRoomModel2.desc,
            "Class2"
        )

    }
}
