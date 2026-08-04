package br.com.usinasantafe.cav.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Routes.ADDRESS_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.ATTENDANT_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.BRAND_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.VEHICLE_FULL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CAR_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CHECK_BREATHALYZER_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.COLAB_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.CONFIG_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.COUNT_BREATHALYZER_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_COLAB_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_EQUIP_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_INITIAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_INVOLVED_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_VEHICLE_INVOLVED_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_VEHICLE_OWN_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DATA_VEHICLE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DETAIL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.DOCUMENT_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.EQUIP_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.EQUIP_SEC_LIST_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INITIAL_MENU_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INPUT_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INVOLVED_WITNESS_COLAB_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.INVOLVED_WITNESS_EXTERNAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.ITEM_DATA_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.LOCAL_SUPPORT_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.NAME_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.NATURE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.OBS_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.OPTION_DATA_LOCAL_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PASSENGER_LIST_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PASSWORD_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PHONE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PHOTO_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.PLATE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.SPLASH_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.STATE_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.SUPPORT_TEAMS_ROUTE
import br.com.usinasantafe.cav.presenter.Routes.TYPE_ACCIDENT_ROUTE
import br.com.usinasantafe.cav.presenter.view.card.attendant.AttendantScreen
import br.com.usinasantafe.cav.presenter.view.card.breathalyzer.check.CheckBreathalyzerScreen
import br.com.usinasantafe.cav.presenter.view.card.breathalyzer.count.CountBreathalyzerScreen
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
import br.com.usinasantafe.cav.presenter.view.card.colab.data.ColabDataScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicleFull.VehicleOwnDataScreen
import br.com.usinasantafe.cav.presenter.view.card.detail.DetailScreen
import br.com.usinasantafe.cav.presenter.view.card.equip.data.EquipDataScreen
import br.com.usinasantafe.cav.presenter.view.card.equip.equip.EquipScreen
import br.com.usinasantafe.cav.presenter.view.card.equipSecList.EquipSecListScreen
import br.com.usinasantafe.cav.presenter.view.card.external.address.AddressScreen
import br.com.usinasantafe.cav.presenter.view.card.external.data.InvolvedDataScreen
import br.com.usinasantafe.cav.presenter.view.card.external.document.DocumentScreen
import br.com.usinasantafe.cav.presenter.view.card.external.name.NameScreen
import br.com.usinasantafe.cav.presenter.view.card.external.phone.PhoneScreen
import br.com.usinasantafe.cav.presenter.view.card.menu.InvolvedWitnessColabScreen
import br.com.usinasantafe.cav.presenter.view.card.menu.InvolvedWitnessExternalScreen
import br.com.usinasantafe.cav.presenter.view.card.obs.ObsScreen
import br.com.usinasantafe.cav.presenter.view.card.passengerList.PassengerListScreen
import br.com.usinasantafe.cav.presenter.view.card.photo.PhotoScreen
import br.com.usinasantafe.cav.presenter.view.card.state.StateScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicle.brand.BrandScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicle.data.VehicleDataScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicle.plate.PlateScreen
import br.com.usinasantafe.cav.presenter.view.card.vehicleFull.VehicleExternalDataScreen
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
                onNavMenuDataInitial = {
                    navActions.navigateToDataInitial()
                }
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
            DATA_VEHICLE_OWN_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            VehicleOwnDataScreen(
                onNavDataEquip = {
                    navActions.navigateToDataEquip(
                        flowNote = FlowNote.EQUIP.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecList(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote =  FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavPassengerList = {
                    navActions.navigateToPassengerList(
                        flowNote = FlowNote.PASSENGER_COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToVehicleFull()
                }
            )
        }

        composable(
            EQUIP_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            EquipScreen(
                onNavMenu = {
                    navActions.navigateToVehicleFull()
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecList(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataEquip = {
                    navActions.navigateToDataEquip(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                }
            )
        }
        
        composable(
            DATA_EQUIP_ROUTE,
            arguments = listOf(
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            EquipDataScreen(
                onNavEquip = {
                    navActions.navigateToEquip(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavEquipSecList = {
                    navActions.navigateToEquipSecList(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                }
            )
        }

        composable(
            COLAB_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            val ordinal = entry.arguments?.getInt(FLOW_NOTE_ARG)!!
            val flowNote = FlowNote.entries[ordinal]
            ColabScreen(
                onNavPassengerList = {
                    navActions.navigateToPassengerList(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavState = {
                    navActions.navigateToState(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = if(flowNote == FlowNote.COLAB) FlowNote.EQUIP.ordinal else entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessColab()
                }
            )
        }

        composable(
            DATA_COLAB_ROUTE,
            arguments = listOf(
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            ColabDataScreen(
                onNavColab = {
                    navActions.navigateToColab(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToState(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPassengerList = {
                    navActions.navigateToPassengerList(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavCheckBreathalyzer = {
                    navActions.navigateToCheckBreathalyzer(
                        option = Option.EDIT.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessColab()
                }
            )
        }

        composable(
            DETAIL_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            val ordinal = entry.arguments?.getInt(FLOW_NOTE_ARG)!!
            val flowNote = FlowNote.entries[ordinal]
            DetailScreen(
                onNavEquip = {
                    navActions.navigateToEquip(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToState(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPhone = {
                    navActions.navigateToPhone(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavColab = {
                    navActions.navigateToColab(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = if(flowNote == FlowNote.EQUIP) FlowNote.COLAB.ordinal else entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataEquip = {
                    navActions.navigateToDataEquip(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavBrand = {
                    navActions.navigateToBrand(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDocument = {
                    navActions.navigateToDocument(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = FlowNote.DRIVER.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataVehicle = {
                    navActions.navigateToDataVehicle(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDataInvolvedEdit = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataInvolvedWitnessExternalInsert = {
                    val ordinal = entry.arguments?.getInt(FLOW_NOTE_ARG)!!
                    val flowNote = FlowNote.entries[ordinal]
                    var idMain = it
                    var idSecondary = 0
                    if(flowNote == FlowNote.PASSENGER_EXTERNAL) {
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                        idSecondary = it
                    }
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = idMain,
                        idSecondary = idSecondary
                    )
                },
                onNavDataInvolvedWitnessColabInsert = {
                    val ordinal = entry.arguments?.getInt(FLOW_NOTE_ARG)!!
                    val flowNote = FlowNote.entries[ordinal]
                    var idMain = it
                    var idSecondary = 0
                    if(flowNote == FlowNote.PASSENGER_COLAB) {
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                        idSecondary = it
                    }
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = idMain,
                        idSecondary = idSecondary
                    )
                },
                onNavDataVehicleOwnInsert = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = it
                    )
                },
                onNavDataVehicleExternalInsert = {
                    navActions.navigateToDataVehicleInvolved(
                        idMain = it
                    )
                },
                onNavDataEquipSec = {
                    navActions.navigateToDataEquip(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavDataPassengerColab = {
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavDataPassengerInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavCheckBreathalyzer = {
                    navActions.navigateToCheckBreathalyzer(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                }
            )
        }

        composable(
            EQUIP_SEC_LIST_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ){ entry ->
            EquipSecListScreen(
                onNavEquip = {
                    navActions.navigateToEquip(
                        option = Option.INSERT.ordinal,
                        flowNote = FlowNote.EQUIP_SEC.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDataEquip = {
                    navActions.navigateToDataEquip(
                        flowNote = FlowNote.EQUIP_SEC.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                }
            )
        }

        composable(
            PASSENGER_LIST_ROUTE,
            arguments = listOf(
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            PassengerListScreen(
                onNavColab = {
                    navActions.navigateToColab(
                        option = Option.INSERT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataVehicleInvolved = {
                    navActions.navigateToDataVehicleInvolved(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDocument = {
                    navActions.navigateToDocument(
                        option = Option.INSERT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = it
                    )
                }
            )
        }

        composable(
            STATE_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            StateScreen(
                onNavColab = {
                    navActions.navigateToColab(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPhone = {
                    navActions.navigateToPhone(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavCheckBreathalyzer = {
                    navActions.navigateToCheckBreathalyzer(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                }
            )
        }

        composable(
            CHECK_BREATHALYZER_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType }
            )
        ){ entry ->
            CheckBreathalyzerScreen(
                onNavState = {
                    navActions.navigateToState(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavCountBreathalyzer = {
                    navActions.navigateToCountBreathalyzer(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                }
            )
        }

        composable(
            COUNT_BREATHALYZER_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            CountBreathalyzerScreen(
                onNavCheckBreathalyzer = {
                    navActions.navigateToCheckBreathalyzer(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataColab = {
                    navActions.navigateToDataColab(
                        flowNote = FlowNote.COLAB.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                }
            )
        }

        composable(
            PLATE_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            PlateScreen(
                onNavMenu = {
                    navActions.navigateToVehicleFull()
                },
                onNavBrand = {
                    navActions.navigateToBrand(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataVehicle = {
                    navActions.navigateToDataVehicle(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                }
            )
        }

        composable(
            BRAND_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            BrandScreen(
                onNavPlate = {
                    navActions.navigateToPlate(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = Option.INSERT.ordinal,
                        flowNote = FlowNote.VEHICLE.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataVehicle = {
                    navActions.navigateToDataVehicle(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!
                    )
                }
            )
        }

        composable(
            DOCUMENT_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            DocumentScreen(
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = FlowNote.VEHICLE.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavName = {
                    navActions.navigateToName(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPassenger = {
                    navActions.navigateToPassengerList(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessExternal()
                }
            )
        }

        composable(
            NAME_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            NameScreen(
                onNavDocument = {
                    navActions.navigateToDocument(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPhone = {
                    navActions.navigateToPhone(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessExternal()
                }
            )
        }

        composable(
            DATA_VEHICLE_INVOLVED_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
            )
        ) { entry ->
            VehicleExternalDataScreen(
                onNavDataVehicle = {
                    navActions.navigateToDataVehicle(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDataDriver = {
                    navActions.navigateToDataInvolved(
                        flowNote = FlowNote.DRIVER.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavPassengerList = {
                    navActions.navigateToPassengerList(
                        flowNote = FlowNote.PASSENGER_EXTERNAL.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToVehicleFull()
                }
            )
        }

        composable(
            DATA_VEHICLE_ROUTE,
            arguments = listOf(
                navArgument(ID_MAIN_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            VehicleDataScreen(
                onNavPlate = {
                    navActions.navigateToPlate(
                        option = Option.EDIT.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavBrand = {
                    navActions.navigateToBrand(
                        option = Option.EDIT.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = Option.EDIT.ordinal,
                        flowNote = FlowNote.VEHICLE.ordinal,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = 0
                    )
                },
                onNavDataVehicleInvolved = {
                    navActions.navigateToDataVehicleInvolved(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                }
            )
        }

        composable(
            DATA_INVOLVED_ROUTE,
            arguments = listOf(
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            InvolvedDataScreen(
                onNavDocument = {
                    navActions.navigateToDocument(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavName = {
                    navActions.navigateToName(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToState(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavPhone = {
                    navActions.navigateToPhone(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavAddress = {
                    navActions.navigateToAddress(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = Option.EDIT.ordinal,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataVehicleInvolved = {
                    navActions.navigateToDataVehicleInvolved(
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                },
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessExternal()
                },
                onNavPassengerList = {
                    navActions.navigateToPassengerList(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                    )
                }
            )
        }

        composable(
            PHONE_ROUTE,
            arguments = listOf(
                navArgument(OPTION_ARG) { type = NavType.IntType },
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            PhoneScreen(
                onNavName = {
                    navActions.navigateToName(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavState = {
                    navActions.navigateToState(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDetail = {
                    navActions.navigateToDetail(
                        option = entry.arguments?.getInt(OPTION_ARG)!!,
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                },
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
                        idMain = entry.arguments?.getInt(ID_MAIN_ARG)!!,
                        idSecondary = entry.arguments?.getInt(ID_SECONDARY_ARG)!!
                    )
                }
            )
        }

        composable(
            ADDRESS_ROUTE,
            arguments = listOf(
                navArgument(FLOW_NOTE_ARG) { type = NavType.IntType },
                navArgument(ID_MAIN_ARG) { type = NavType.IntType },
                navArgument(ID_SECONDARY_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            AddressScreen(
                onNavDataInvolved = {
                    navActions.navigateToDataInvolved(
                        flowNote = entry.arguments?.getInt(FLOW_NOTE_ARG)!!,
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
                    navActions.navigateToVehicleFull()
                }
            )
        }

        composable(VEHICLE_FULL_ROUTE) {
            VehicleFullScreen(
                onNavLocalSupport = {
                    navActions.navigateToLocalSupport()
                },
                onNavInvolvedWitness = {
                    navActions.navigateToInvolvedWitnessColab()
                },
                onNavEquip = {
                    navActions.navigateToEquip(
                        option = Option.INSERT.ordinal,
                        flowNote = FlowNote.EQUIP.ordinal,
                        idMain = 0,
                        idSecondary = 0
                    )
                },
                onNavDataVehicleOwn = {
                    navActions.navigateToDataVehicleOwn(
                        idMain = it
                    )
                },
                onNavPlate = {
                    navActions.navigateToPlate(
                        option = Option.INSERT.ordinal,
                        idMain = 0,
                    )
                },
                onNavDataVehicleInvolved = {
                    navActions.navigateToDataVehicleInvolved(
                        idMain = it
                    )
                }
            )
        }

        composable(INVOLVED_WITNESS_COLAB_ROUTE) {
            InvolvedWitnessColabScreen(
                onNavColab = {
                    navActions.navigateToColab(
                        option = Option.INSERT.ordinal,
                        flowNote = it.ordinal,
                        idMain = 0,
                        idSecondary = 0
                    )
                },
                onNavDataColab = { flowNote, id ->
                    navActions.navigateToDataColab(
                        flowNote = flowNote.ordinal,
                        idMain = id,
                        idSecondary = 0
                    )
                },
                onNavInvolvedWitnessExternal = {
                    navActions.navigateToInvolvedWitnessExternal()
                },
                onNavVehicleFull = {
                    navActions.navigateToVehicleFull()
                }
            )
        }

        composable(INVOLVED_WITNESS_EXTERNAL_ROUTE) {
            InvolvedWitnessExternalScreen(
                onNavDocument = {
                    navActions.navigateToDocument(
                        option = Option.INSERT.ordinal,
                        flowNote = FlowNote.INVOLVED_EXTERNAL.ordinal,
                        idMain = 0,
                        idSecondary = 0
                    )
                },
                onNavName = {
                    navActions.navigateToName(
                        option = Option.INSERT.ordinal,
                        flowNote = FlowNote.WITNESS_EXTERNAL.ordinal,
                        idMain = 0,
                        idSecondary = 0
                    )
                },
                onNavDataInvolved = { flowNote, id ->
                    navActions.navigateToDataInvolved(
                        flowNote = flowNote.ordinal,
                        idMain = id,
                        idSecondary = 0
                    )
                },
                onNavInvolvedWitnessColab = {
                    navActions.navigateToInvolvedWitnessColab()
                },
                onNavObs = {
                    navActions.navigateToObs()
                }
            )
        }

        composable(OBS_ROUTE) {
            ObsScreen(
                onNavMenu = {
                    navActions.navigateToInvolvedWitnessExternal()
                },
                onNavPhoto = {
                    navActions.navigateToPhoto()
                }
            )
        }

        composable(PHOTO_ROUTE) {
            PhotoScreen(
                onNavObs = {
                    navActions.navigateToObs()
                },
                onNavSplash = {
                    navActions.navigateToSplash()
                }
            )
        }

        //////////////////////////////////////////////////////////////////////

    }
}