package br.com.usinasantafe.cav

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class ConfigFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

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

            composeTestRule.onNodeWithText("CONFIGURAÇÃO")
                .performClick()

            Log.d("TestDebug", "Position 3")

            composeTestRule.waitUntilTimeout(10_000)

        }
}
