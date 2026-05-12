package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Screens.ATTENDANT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_INITIAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INITIAL_MENU_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.NATURE_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSWORD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SPLASH_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.TYPE_ACCIDENT_SCREEN

object Screens {
    const val SPLASH_SCREEN = "splashScreen"
    const val INITIAL_MENU_SCREEN = "initialMenuScreen"
    const val PASSWORD_SCREEN = "passwordScreen"
    const val CONFIG_SCREEN = "configScreen"
    const val ATTENDANT_SCREEN = "attendantScreen"
    const val CAR_SCREEN = "carScreen"
    const val DATA_INITIAL_SCREEN = "dataInitialScreen"
    const val NATURE_SCREEN = "natureScreen"
    const val TYPE_ACCIDENT_SCREEN = "typeAccidentScreen"
}

object Args {
    const val OPTION_ARG = "flow"
}
object Routes {
    const val SPLASH_ROUTE = SPLASH_SCREEN
    const val INITIAL_MENU_ROUTE = INITIAL_MENU_SCREEN
    const val PASSWORD_ROUTE = PASSWORD_SCREEN
    const val CONFIG_ROUTE = CONFIG_SCREEN
    const val ATTENDANT_ROUTE = "$ATTENDANT_SCREEN/{$OPTION_ARG}"
    const val CAR_ROUTE = "$CAR_SCREEN/{$OPTION_ARG}"
    const val DATA_INITIAL_ROUTE = DATA_INITIAL_SCREEN
    const val NATURE_ROUTE = NATURE_SCREEN
    const val TYPE_ACCIDENT_ROUTE = TYPE_ACCIDENT_SCREEN
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

    fun navigateToAttendant(
        option: Int
    ) {
        navController.navigate("$ATTENDANT_SCREEN/${option}")
    }

    fun navigateToCar(
        option: Int
    ) {
        navController.navigate("$CAR_SCREEN/${option}")
    }

    fun navigateToNature() {
        navController.navigate(NATURE_SCREEN)
    }

    fun navigateToTypeAccident() {
        navController.navigate(TYPE_ACCIDENT_SCREEN)
    }

    //////////////////////////////////////////////////////////////////////

    ///////////////////////// Menu Card //////////////////////////////////

    fun navigateToDataInitial() {
        navController.navigate(DATA_INITIAL_SCREEN)
    }


    //////////////////////////////////////////////////////////////////////

}