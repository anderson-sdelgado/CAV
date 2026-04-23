package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INITIAL_MENU_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSWORD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SPLASH_SCREEN

object Screens {
    const val SPLASH_SCREEN = "splashScreen"
    const val INITIAL_MENU_SCREEN = "initialMenuScreen"
    const val PASSWORD_SCREEN = "passwordScreen"
    const val CONFIG_SCREEN = "configScreen"
}

object Routes {
    const val SPLASH_ROUTE = SPLASH_SCREEN
    const val INITIAL_MENU_ROUTE = INITIAL_MENU_SCREEN
    const val PASSWORD_ROUTE = PASSWORD_SCREEN
    const val CONFIG_ROUTE = CONFIG_SCREEN
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

    ////////////////////////////////////////////////////////////////////

}