package br.com.usinasantafe.cav.integration

import android.util.Log
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import br.com.usinasantafe.cav.presenter.MainActivity
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.minutes

@HiltAndroidTest
class PhotoFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun flow() =
        runTest(
            timeout = 10.minutes
        ) {

            Log.d("TestDebug", "Position 1")

            composeTestRule.waitUntilTimeout(30_000)

        }
}