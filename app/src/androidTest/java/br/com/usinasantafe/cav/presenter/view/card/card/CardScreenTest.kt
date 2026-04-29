package br.com.usinasantafe.cav.presenter.view.card.card

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import br.com.usinasantafe.cav.HiltTestActivity
import br.com.usinasantafe.cav.utils.waitUntilTimeout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test

@HiltAndroidTest
class CardScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @Test
    fun check_open_screen() =
        runTest {

            hiltRule.inject()

            setContent()

            composeTestRule.waitUntilTimeout(20_000)

        }

    private fun setContent(){
        composeTestRule.setContent {
            CardScreen()
        }
    }
}