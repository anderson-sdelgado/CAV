package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Args.ID_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.presenter.Screens.ATTENDANT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_FULL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.COLAB_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_INITIAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DETAIL_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.EQUIP_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.EQUIP_SEC_LIST_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INITIAL_MENU_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INPUT_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.ITEM_DATA_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.LOCAL_SUPPORT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.NATURE_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.OPTION_DATA_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSENGER_LIST_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSWORD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SPLASH_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.STATE_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SUPPORT_TEAMS_SCREEN
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
    const val LOCAL_SUPPORT_SCREEN = "localSupportScreen"
    const val LOCAL_SCREEN = "localScreen"
    const val INPUT_LOCAL_SCREEN = "inputLocalScreen"
    const val OPTION_DATA_LOCAL_SCREEN = "optionDataLocalScreen"
    const val ITEM_DATA_LOCAL_SCREEN = "itemDataLocalScreen"
    const val SUPPORT_TEAMS_SCREEN = "supportTeamsScreen"
    const val CAR_FULL_SCREEN = "carFullScreen"
    const val COLAB_VEHICLE_OWN_SCREEN = "colabVehicleOwnScreen"
    const val EQUIP_VEHICLE_OWN_SCREEN = "equipVehicleOwnScreen"
    const val DATA_VEHICLE_OWN_SCREEN = "dataVehicleOwnScreen"
    const val DETAIL_VEHICLE_OWN_SCREEN = "detailVehicleOwnScreen"
    const val EQUIP_SEC_LIST_VEHICLE_OWN_SCREEN = "equipSecListVehicleOwnScreen"
    const val PASSENGER_LIST_VEHICLE_OWN_SCREEN = "passengerListVehicleOwnScreen"
    const val STATE_VEHICLE_OWN_SCREEN = "stateVehicleOwnScreen"
}

object Args {
    const val OPTION_ARG = "option"
    const val ID_ARG = "id"
    const val TYPE_ARG = "type"
    const val TYPE_DETAIL_ARG = "typeDetail"
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
    const val LOCAL_SUPPORT_ROUTE = LOCAL_SUPPORT_SCREEN
    const val LOCAL_ROUTE = LOCAL_SCREEN
    const val INPUT_LOCAL_ROUTE = INPUT_LOCAL_SCREEN
    const val OPTION_DATA_LOCAL_ROUTE = OPTION_DATA_LOCAL_SCREEN
    const val ITEM_DATA_LOCAL_ROUTE = "$ITEM_DATA_LOCAL_SCREEN/{$ID_ARG}"
    const val SUPPORT_TEAMS_ROUTE = SUPPORT_TEAMS_SCREEN
    const val CAR_FULL_ROUTE = CAR_FULL_SCREEN
    const val COLAB_ROUTE = "$COLAB_VEHICLE_OWN_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}"
    const val EQUIP_ROUTE = "$EQUIP_VEHICLE_OWN_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}"
    const val DATA_VEHICLE_OWN_ROUTE = DATA_VEHICLE_OWN_SCREEN
    const val DETAIL_VEHICLE_OWN_ROUTE = "$DETAIL_VEHICLE_OWN_SCREEN/{$OPTION_ARG}/{$TYPE_DETAIL_ARG}"
    const val EQUIP_SEC_LIST_VEHICLE_OWN_ROUTE = "$EQUIP_SEC_LIST_VEHICLE_OWN_SCREEN/{$OPTION_ARG}"
    const val PASSENGER_LIST_VEHICLE_OWN_ROUTE = "$PASSENGER_LIST_VEHICLE_OWN_SCREEN/{$OPTION_ARG}"
    const val STATE_VEHICLE_OWN_ROUTE = "$STATE_VEHICLE_OWN_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}"
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
        navController.navigate("$ATTENDANT_SCREEN/$option")
    }

    fun navigateToCar(
        option: Int
    ) {
        navController.navigate("$CAR_SCREEN/$option")
    }

    fun navigateToNature() {
        navController.navigate(NATURE_SCREEN)
    }

    fun navigateToTypeAccident() {
        navController.navigate(TYPE_ACCIDENT_SCREEN)
    }

    fun navigateToLocal(){
        navController.navigate(LOCAL_SCREEN)
    }

    fun navigateToInputLocal(){
        navController.navigate(INPUT_LOCAL_SCREEN)
    }

    fun navigateToOptionDataLocal() {
        navController.navigate(OPTION_DATA_LOCAL_SCREEN)
    }

    fun navigateToItemDataLocal(
        id: Int
    ) {
        navController.navigate("$ITEM_DATA_LOCAL_SCREEN/$id")
    }

    fun navigateToSupportTeams() {
        navController.navigate(SUPPORT_TEAMS_SCREEN)
    }

    fun navigateToEquipVehicleOwn(
        option: Int,
        type: Int
    ){
        navController.navigate("$EQUIP_VEHICLE_OWN_SCREEN/$option/$type")
    }

    fun navigateToColabVehicleOwn(
        option: Int,
        type: Int
    ){
        navController.navigate("$COLAB_VEHICLE_OWN_SCREEN/$option/$type")
    }

    fun navigateToDataVehicleOwn(){
        navController.navigate(DATA_VEHICLE_OWN_SCREEN)
    }

    fun navigateToDetailVehicleOwn(
        option: Int,
        typeDetail: Int
    ){
        navController.navigate("$DETAIL_VEHICLE_OWN_SCREEN/$option/$typeDetail")
    }

    fun navigateToEquipSecListVehicleOwn(
        option: Int
    ){
        navController.navigate("$EQUIP_SEC_LIST_VEHICLE_OWN_SCREEN/$option")
    }

    fun navigateToPassengerListVehicleOwn(
        option: Int
    ){
        navController.navigate("$PASSENGER_LIST_VEHICLE_OWN_SCREEN/$option")
    }

    fun navigateToStateVehicleOwn(
        option: Int,
        type: Int
    ){
        navController.navigate("$STATE_VEHICLE_OWN_SCREEN/$option/$type")
    }


    //////////////////////////////////////////////////////////////////////

    ///////////////////////// Menu Card //////////////////////////////////

    fun navigateToDataInitial() {
        navController.navigate(DATA_INITIAL_SCREEN)
    }

    fun navigateToLocalSupport() {
        navController.navigate(LOCAL_SUPPORT_SCREEN)
    }

    fun navigateToCarFull() {
        navController.navigate(CAR_FULL_SCREEN)
    }

    //////////////////////////////////////////////////////////////////////

}