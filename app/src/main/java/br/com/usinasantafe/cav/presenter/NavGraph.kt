package br.com.usinasantafe.cav.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.usinasantafe.cav.presenter.Routes.CONFIG_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INITIAL_MENU_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PASSWORD_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.SPLASH_ROUTE
import br.com.usinasantafe.cav.presenter.view.configuration.config.ConfigScreen
import br.com.usinasantafe.cav.presenter.view.configuration.initial.InitialMenuScreen
import br.com.usinasantafe.cav.presenter.view.configuration.password.PasswordScreen
import br.com.usinasantafe.cav.presenter.view.splash.SplashScreen


@Composable
fun NavigationGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = SPLASH_ROUTE,
    navActions: NavigationActions = remember(navHostController) {
        NavigationActions(navHostController)
    }
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

        ///////////////////////// Splash //////////////////////////////////

        composable(SPLASH_ROUTE) {
            SplashScreen(
                onNavInitialMenu = {
                    navActions.navigateToInitialMenu()
                },
            )
        }

        ////////////////////////////////////////////////////////////////////

        ///////////////////////// Config //////////////////////////////////

        composable(INITIAL_MENU_ROUTE) {
            InitialMenuScreen(
                onNavPassword = {
                    navActions.navigateToPassword()
                },
            )
        }

        composable(PASSWORD_ROUTE) {
            PasswordScreen(
                onNavInitialMenu = {
                    navActions.navigateToInitialMenu()
                },
                onNavConfig = {
                    navActions.navigateToConfig()
                }
            )
        }

        composable(CONFIG_ROUTE) {
            ConfigScreen(
                onNavInitialMenu = {
                    navActions.navigateToInitialMenu()
                }
            )
        }

        //////////////////////////////////////////////////////////////////////

    }
}