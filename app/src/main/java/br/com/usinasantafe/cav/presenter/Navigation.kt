package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.presenter.Screens.ATTENDANT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_FULL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.COLAB_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_COLAB_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_EQUIP_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_FOREIGN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_INITIAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_VEHICLE_FOREIGN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_VEHICLE_OWN_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_VEHICLE_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DETAIL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.EQUIP_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.EQUIP_SEC_LIST_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INITIAL_MENU_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INPUT_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.ITEM_DATA_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.LOCAL_SUPPORT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.NATURE_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.OPTION_DATA_LOCAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSENGER_LIST_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PASSWORD_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PLATE_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.SPLASH_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.STATE_SCREEN
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
    const val COLAB_SCREEN = "colabScreen"
    const val EQUIP_SCREEN = "equipVehicleOwnScreen"
    const val DATA_VEHICLE_OWN_SCREEN = "dataVehicleOwnScreen"
    const val DETAIL_SCREEN = "detailScreen"
    const val EQUIP_SEC_LIST_SCREEN = "equipSecListScreen"
    const val PASSENGER_LIST_SCREEN = "passengerListScreen"
    const val STATE_SCREEN = "stateScreen"
    const val DATA_EQUIP_SCREEN = "dataEquipScreen"
    const val DATA_COLAB_SCREEN = "dataColabScreen"
    const val DATA_VEHICLE_FOREIGN_SCREEN = "dataVehicleForeignScreen"
    const val PLATE_SCREEN = "plateScreen"
    const val DATA_VEHICLE_SCREEN = "dataVehicleScreen"
    const val DATA_FOREIGN_SCREEN = "dataForeignScreen"
}

object Args {
    const val OPTION_ARG = "option"
    const val ID_MAIN_ARG = "idMain"
    const val ID_SECONDARY_ARG = "idSecondary"
    const val TYPE_ARG = "type"
    const val TYPE_DETAIL_ARG = "typeDetail"
    const val TYPE_PEOPLE_ARG = "typePeople"
    const val TYPE_VEHICLE_ARG = "typeVehicle"
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
    const val ITEM_DATA_LOCAL_ROUTE = "$ITEM_DATA_LOCAL_SCREEN/{$ID_MAIN_ARG}"
    const val SUPPORT_TEAMS_ROUTE = SUPPORT_TEAMS_SCREEN
    const val CAR_FULL_ROUTE = CAR_FULL_SCREEN
    const val COLAB_ROUTE = "$COLAB_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val EQUIP_ROUTE = "$EQUIP_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val DATA_VEHICLE_OWN_ROUTE = "$DATA_VEHICLE_OWN_SCREEN/{$ID_MAIN_ARG}"
    const val DETAIL_ROUTE = "$DETAIL_SCREEN/{$OPTION_ARG}/{$TYPE_DETAIL_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val EQUIP_SEC_LIST_ROUTE = "$EQUIP_SEC_LIST_SCREEN/{$OPTION_ARG}/{$ID_MAIN_ARG}"
    const val PASSENGER_LIST_ROUTE = "$PASSENGER_LIST_SCREEN/{$OPTION_ARG}/{$ID_MAIN_ARG}"
    const val STATE_ROUTE = "$STATE_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val DATA_EQUIP_ROUTE = "$DATA_EQUIP_SCREEN/{$ID_MAIN_ARG}"
    const val DATA_COLAB_ROUTE = "$DATA_COLAB_SCREEN/{$ID_MAIN_ARG}"
    const val DATA_VEHICLE_FOREIGN_ROUTE = "$DATA_VEHICLE_FOREIGN_SCREEN/{$ID_MAIN_ARG}"
    const val PLATE_ROUTE = "$PLATE_SCREEN/{$OPTION_ARG}/{$TYPE_ARG}/{$ID_MAIN_ARG}"
    const val DATA_VEHICLE_ROUTE = "$DATA_VEHICLE_SCREEN/{$ID_MAIN_ARG}"
    const val DATA_FOREIGN_ROUTE = "$DATA_FOREIGN_SCREEN/{$TYPE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
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

    fun navigateToEquip(
        option: Int,
        type: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$EQUIP_SCREEN/$option/$type/$idMain/$idSecondary")
    }

    fun navigateToColab(
        option: Int,
        type: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$COLAB_SCREEN/$option/$type/$idMain/$idSecondary")
    }

    fun navigateToDataVehicleOwn(
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_OWN_SCREEN/$idMain")
    }

    fun navigateToDetail(
        option: Int,
        typeDetail: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$DETAIL_SCREEN/$option/$typeDetail/$idMain/$idSecondary")
    }

    fun navigateToEquipSecList(
        option: Int,
        idMain: Int,
    ){
        navController.navigate("$EQUIP_SEC_LIST_SCREEN/$option/$idMain")
    }

    fun navigateToPassengerList(
        option: Int,
        typeVehicle: Int,
        idMain: Int,
    ){
        navController.navigate("$PASSENGER_LIST_SCREEN/$option/$typeVehicle/$idMain")
    }

    fun navigateToState(
        option: Int,
        typePeople: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$STATE_SCREEN/$option/$typePeople/$idMain/$idSecondary")
    }

    fun navigateToDataEquip(
        type: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_EQUIP_SCREEN/$type/$idMain/$idSecondary")
    }

    fun navigateToDataColab(
        type: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_COLAB_SCREEN/$type/$idMain/$idSecondary")
    }

    fun navigateToDataVehicleForeign(
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_FOREIGN_SCREEN/$idMain")
    }

    fun navigateToPlate(
        option: Int,
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_FOREIGN_SCREEN/$option/$idMain")
    }

    fun navigateToBrand(
        option: Int,
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_FOREIGN_SCREEN/$option/$idMain")
    }

    fun navigateToDataVehicle(
        idMain: Int,
    ) {
        navController.navigate("$DATA_COLAB_SCREEN/$idMain")
    }

    fun navigateToDataForeign(
        type: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_FOREIGN_SCREEN/$type/$idMain/$idSecondary")
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