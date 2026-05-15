package br.com.usinasantafe.cav.presenter.view.card.local

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.rule.GrantPermissionRule
import br.com.usinasantafe.cav.HiltTestActivity
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class LocalScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @get:Rule(order = 2)
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @Test
    fun check_open_screen() =
        runTest(
            timeout = 2.minutes
        ) {

            hiltRule.inject()

            setContent()

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("LOCALIZAÇÃO ATUAL")
                .performClick()

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("CAPTURAR LOCALIZAÇÃO")
                .performClick()

            composeTestRule.waitUntilTimeout(3_000)

            composeTestRule.onNodeWithText("SIM")
                .performClick()

            composeTestRule.waitUntilTimeout(10_000)

        }

    private fun setContent(){
        composeTestRule.setContent {
            LocalScreen(
                onNavMenu = {},
                onNavInputLocal = {}
            )
        }
    }
}