package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Screens.ATTENDANT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CARD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INITIAL_MENU_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSWORD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SPLASH_SCREEN

object Screens {
    const val SPLASH_SCREEN = "splashScreen"
    const val INITIAL_MENU_SCREEN = "initialMenuScreen"
    const val PASSWORD_SCREEN = "passwordScreen"
    const val CONFIG_SCREEN = "configScreen"
    const val ATTENDANT_SCREEN = "attendantScreen"
    const val CAR_SCREEN = "carScreen"
    const val CARD_SCREEN = "cardScreen"
}

object Routes {
    const val SPLASH_ROUTE = SPLASH_SCREEN
    const val INITIAL_MENU_ROUTE = INITIAL_MENU_SCREEN
    const val PASSWORD_ROUTE = PASSWORD_SCREEN
    const val CONFIG_ROUTE = CONFIG_SCREEN
    const val ATTENDANT_ROUTE = ATTENDANT_SCREEN
    const val CAR_ROUTE = CAR_SCREEN
    const val CARD_ROUTE = CARD_SCREEN
}

class NavigationActions(private val navController: NavHostController) {

    ///////////////////////// Splash //////////////////////////////////

    fun navigateToSplash() {
        navController.navigate(SPLASH_SCREEN)
    }

    ////////////////////////////////////////////////////////////////////

    ///////////////////////// Config //////////////////////////////////

    fun navigateToPassword() {
        navController.navigate(PASSWORD_SCREEN)
    }

    fun navigateToInitialMenu() {
        navController.navigate(INITIAL_MENU_SCREEN)
    }

    fun navigateToConfig() {
        navController.navigate(CONFIG_SCREEN)
    }

    //////////////////////////////////////////////////////////////////////

    ////////////////////////////// Card //////////////////////////////////

    fun navigateToAttendant() {
        navController.navigate(ATTENDANT_SCREEN)
    }

    fun navigateToCar() {
        navController.navigate(CAR_SCREEN)
    }

    fun navigateToCard() {
        navController.navigate(CARD_SCREEN)
    }

    //////////////////////////////////////////////////////////////////////

}