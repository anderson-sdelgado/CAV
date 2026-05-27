package br.com.usinasantafe.cav.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.presenter.Routes.ATTENDANT_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CAR_FULL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CAR_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.COLAB_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CONFIG_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_INITIAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DETAIL_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.EQUIP_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.EQUIP_SEC_LIST_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INITIAL_MENU_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INPUT_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.ITEM_DATA_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.LOCAL_SUPPORT_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.NATURE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.OPTION_DATA_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PASSENGER_LIST_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PASSWORD_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.SPLASH_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.STATE_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.SUPPORT_TEAMS_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.TYPE_ACCIDENT_ROUTE
import br.com.usinasantafe.cav.presenter.view.card.attendant.AttendantScreen
import br.com.usinasantafe.cav.presenter.view.card.car.CarScreen
import br.com.usinasantafe.cav.presenter.view.card.dataLocal.ItemDataLocalScreen
import br.com.usinasantafe.cav.presenter.view.card.dataLocal.OptionDataLocalScreen
import br.com.usinasantafe.cav.presenter.view.card.local.InputLocalScreen
import br.com.usinasantafe.cav.presenter.view.card.local.LocalScreen
import br.com.usinasantafe.cav.presenter.view.card.menu.VehicleFullScreen
import br.com.usinasantafe.cav.presenter.view.card.menu.DataInitialScreen
import br.com.usinasantafe.cav.presenter.view.card.menu.LocalSupportScreen
import br.com.usinasantafe.cav.presenter.view.card.nature.NatureScreen
import br.com.usinasantafe.cav.presenter.view.card.supportTeams.SupportTeamsScreen
import br.com.usinasantafe.cav.presenter.view.card.typeAccident.TypeAccidentScreen
import br.com.usinasantafe.cav.presenter.view.card.colab.colab.ColabScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicleFull.VehicleOwnDataScreen
import br.com.usinasantafe.cav.presenter.view.card.detail.DetailVehicleOwnScreen
import br.com.usinasantafe.cav.presenter.view.card.equip.equip.EquipScreen
import br.com.usinasantafe.cav.presenter.view.card.equip.equipSecList.EquipSecListScreen
import br.com.usinasantafe.cav.presenter.view.card.colab.passengerList.PassengerListOwnScreen
import br.com.usinasantafe.cav.presenter.view.card.state.StateColabOwnScreen
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
                onNavAttendant = {
                    navActions.navigateToAttendant(
                        option = Option.INSERT.ordinal
                    )
                }
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

        ////////////////////////////// Card //////////////////////////////////

        composable(
            ATTENDANT_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            AttendantScreen(
                onNavCar = {
                    navActions.navigateToCar(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                    )
                },
                onNavInitialMenu = {
                    navActions.navigateToInitialMenu()
                },
                onNavMenu = {
                    navActions.navigateToDataInitial()
                }
            )
        }

        composable(
            CAR_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            CarScreen(
                onNavAttendant = {
                    navActions.navigateToAttendant(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToDataInitial()
                }
            )
        }

        composable(NATURE_ROUTE) {
            NatureScreen(
                onNavMenu = {
                    navActions.navigateToDataInitial()
                }
            )
        }

        composable(TYPE_ACCIDENT_ROUTE) {
            TypeAccidentScreen(
                onNavMenu = {
                    navActions.navigateToDataInitial()
                }
            )
        }

        composable(LOCAL_ROUTE) {
            LocalScreen(
                onNavMenu = {
                    navActions.navigateToLocalSupport()
                },
                onNavInputLocal = {
                    navActions.navigateToInputLocal()
                }
            )
        }

        composable(INPUT_LOCAL_ROUTE) {
            InputLocalScreen(
                onNavCard = {
                    navActions.navigateToLocalSupport()
                }
            )
        }

        composable(OPTION_DATA_LOCAL_ROUTE) {
            OptionDataLocalScreen(
                onNavItem = {
                    navActions.navigateToItemDataLocal(it)
                },
                onNavMenu = {
                    navActions.navigateToLocalSupport()
                }
            )
        }

        composable(
            ITEM_DATA_LOCAL_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType }
            )
        ) {
            ItemDataLocalScreen(
                onNavOption = {
                    navActions.navigateToOptionDataLocal()
                }
            )
        }

        composable(SUPPORT_TEAMS_ROUTE) {
            SupportTeamsScreen(
                onNavMenu = {
                    navActions.navigateToLocalSupport()
                }
            )
        }

        composable(
            EQUIP_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(TYPE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            EquipScreen(
                onNavMenu = {
                    navActions.navigateToCarFull()
                },
                onNavData = {
                    navActions.navigateToDataVehicleOwn(
                        id = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecListVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDetail = {
                    val index = entry.arguments?.getInt(TYPE_ARG)!!
                    val type = Type.entries[index]
                    val typeDetail = when(type){
                        Type.MAIN -> TypeDetail.VEHICLE
                        Type.SECONDARY -> TypeDetail.VEHICLE_SEC
                    }
                    navActions.navigateToDetailVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        typeDetail = typeDetail.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
            )
        }

        composable(
            COLAB_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(TYPE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            ColabScreen(
                onNavPassengerList = {
                    navActions.navigateToPassengerListVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecListVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToStateVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = entry.arguments?.getInt(TYPE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavData = {
                    navActions.navigateToDataVehicleOwn(
                        id = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                }
            )
        }

        composable(
            DATA_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            VehicleOwnDataScreen(
                onNavColab = {
                    navActions.navigateToColabVehicleOwn(
                        option = Option.EDIT.ordinal,
                        type = Type.MAIN.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavEquip = {
                    navActions.navigateToEquipVehicleOwn(
                        option = Option.EDIT.ordinal,
                        type = Type.MAIN.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecListVehicleOwn(
                        option = Option.EDIT.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavPassengerList = {
                    navActions.navigateToEquipSecListVehicleOwn(
                        option = Option.EDIT.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToCarFull()
                }
            )
        }

        composable(
            DETAIL_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(TYPE_DETAIL_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            val index = entry.arguments?.getInt(TYPE_DETAIL_ARG)!!
            val typeDetail = TypeDetail.entries[index]
            val type = when(typeDetail){
                TypeDetail.VEHICLE -> Type.MAIN
                TypeDetail.VEHICLE_SEC -> Type.SECONDARY
                TypeDetail.PEOPLE -> Type.MAIN
                TypeDetail.PASSENGER -> Type.SECONDARY
            }
            DetailVehicleOwnScreen(
                onNavData = {
                    navActions.navigateToDataVehicleOwn(
                        id = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavEquip = {
                    navActions.navigateToEquipVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = type.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToStateVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = type.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToPassengerListVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavPassengerList = {
                    navActions.navigateToEquipSecListVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                }
            )
        }

        composable(
            EQUIP_SEC_LIST_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ){ entry ->
            EquipSecListScreen(
                onNavDetail = {
                    navActions.navigateToDetailVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        typeDetail = TypeDetail.VEHICLE.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavEquip = {
                    navActions.navigateToEquipVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = Type.SECONDARY.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavData = {
                    navActions.navigateToDataVehicleOwn(
                        id = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavColab = {
                    navActions.navigateToColabVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        type = Type.MAIN.ordinal,
                        idSecondary = 0
                    )
                }
            )
        }

        composable(
            PASSENGER_LIST_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            PassengerListOwnScreen(
                onNavColab = {
                    navActions.navigateToColabVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = Type.SECONDARY.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetailVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        typeDetail = TypeDetail.PEOPLE.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavData = {
                    navActions.navigateToDataVehicleOwn(
                        id = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavMenu = {
                    navActions.navigateToCarFull()
                }
            )
        }

        composable(
            STATE_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(TYPE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            StateColabOwnScreen(
                onNavColab = {
                    navActions.navigateToColabVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        type = entry.arguments?.getInt(TYPE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    val index = entry.arguments?.getInt(TYPE_ARG)!!
                    val type = Type.entries[index]
                    val typeDetail = when(type){
                        Type.MAIN -> TypeDetail.PEOPLE
                        Type.SECONDARY -> TypeDetail.PASSENGER
                    }
                    navActions.navigateToDetailVehicleOwn(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        typeDetail = typeDetail.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                }
            )
        }

        //////////////////////////////////////////////////////////////////////

        ///////////////////////////// Menu Card //////////////////////////////

        composable(DATA_INITIAL_ROUTE) {
            DataInitialScreen(
                onNavSplash = {
                    navActions.navigateToSplash()
                },
                onNavAttendant = {
                    navActions.navigateToAttendant(
                        option = Option.EDIT.ordinal
                    )
                },
                onNavCar = {
                    navActions.navigateToCar(
                        option = Option.EDIT.ordinal
                    )
                },
                onNavNature = {
                    navActions.navigateToNature()
                },
                onNavTypeAccident = {
                    navActions.navigateToTypeAccident()
                },
                onNavLocalSupport = {
                    navActions.navigateToLocalSupport()
                }
            )
        }

        composable(LOCAL_SUPPORT_ROUTE) {
            LocalSupportScreen(
                onNavDataInitial = {
                    navActions.navigateToDataInitial()
                },
                onNavLocal = {
                    navActions.navigateToLocal()
                },
                onNavDataLocal = {
                    navActions.navigateToOptionDataLocal()
                },
                onNavSupportTeams = {
                    navActions.navigateToSupportTeams()
                },
                onNavCarFull = {
                    navActions.navigateToCarFull()
                }
            )
        }

        composable(CAR_FULL_ROUTE) {
            VehicleFullScreen(
                onNavLocalSupport = {
                    navActions.navigateToLocalSupport()
                },
                onNavInvolvedWitness = {},
                onNavEquip = {
                    navActions.navigateToEquipVehicleOwn(
                        option = Option.INSERT.ordinal,
                        type = Type.MAIN.ordinal,
                        idMain = 0,
                        idSecondary = 0
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        id = it
                    )
                }
            )
        }

        //////////////////////////////////////////////////////////////////////

    }
}