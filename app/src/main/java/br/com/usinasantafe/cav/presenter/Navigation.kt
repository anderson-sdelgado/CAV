package br.com.usinasantafe.cav.presenter

import androidx.navigation.NavHostController
import br.com.usinasantafe.cav.presenter.Args.FLOW_NOTE_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_MAIN_ARG
import br.com.usinasantafe.cav.presenter.Args.ID_SECONDARY_ARG
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Screens.ADDRESS_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.ATTENDANT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.VEHICLE_FULL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CAR_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.COLAB_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.CONFIG_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_COLAB_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_EQUIP_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_INVOLVED_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_INITIAL_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DATA_VEHICLE_INVOLVED_SCREEN
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
import br.com.usinasantafe.cav.presenter.Screens.BRAND_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.DOCUMENT_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.INVOLVED_WITNESS_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.NAME_SCREEN
import br.com.usinasantafe.cav.presenter.Screens.PHONE_SCREEN

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
    const val VEHICLE_FULL_SCREEN = "vehicleFullScreen"
    const val COLAB_SCREEN = "colabScreen"
    const val EQUIP_SCREEN = "equipVehicleOwnScreen"
    const val DATA_VEHICLE_OWN_SCREEN = "dataVehicleOwnScreen"
    const val DETAIL_SCREEN = "detailScreen"
    const val EQUIP_SEC_LIST_SCREEN = "equipSecListScreen"
    const val PASSENGER_LIST_SCREEN = "passengerListScreen"
    const val STATE_SCREEN = "stateScreen"
    const val DATA_EQUIP_SCREEN = "dataEquipScreen"
    const val DATA_COLAB_SCREEN = "dataColabScreen"
    const val DATA_VEHICLE_INVOLVED_SCREEN = "dataVehicleInvolvedScreen"
    const val PLATE_SCREEN = "plateScreen"
    const val DATA_VEHICLE_SCREEN = "dataVehicleScreen"
    const val DATA_INVOLVED_SCREEN = "dataInvolvedScreen"
    const val BRAND_SCREEN = "brandScreen"
    const val DOCUMENT_SCREEN = "documentScreen"
    const val NAME_SCREEN = "nameScreen"
    const val PHONE_SCREEN = "phoneScreen"
    const val ADDRESS_SCREEN = "addressScreen"
    const val INVOLVED_WITNESS_SCREEN = "InvolvedScreen"
}

object Args {
    const val OPTION_ARG = "option"
    const val FLOW_NOTE_ARG = "flowNote"
    const val ID_MAIN_ARG = "idMain"
    const val ID_SECONDARY_ARG = "idSecondary"
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
    const val VEHICLE_FULL_ROUTE = VEHICLE_FULL_SCREEN
    const val DATA_VEHICLE_OWN_ROUTE = "$DATA_VEHICLE_OWN_SCREEN/{$ID_MAIN_ARG}"
    const val DATA_VEHICLE_INVOLVED_ROUTE = "$DATA_VEHICLE_INVOLVED_SCREEN/{$ID_MAIN_ARG}"
    const val COLAB_ROUTE = "$COLAB_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val EQUIP_ROUTE = "$EQUIP_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val DETAIL_ROUTE = "$DETAIL_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val EQUIP_SEC_LIST_ROUTE = "$EQUIP_SEC_LIST_SCREEN/{$ID_MAIN_ARG}"
    const val PASSENGER_LIST_ROUTE = "$PASSENGER_LIST_SCREEN/{$ID_MAIN_ARG}"
    const val STATE_ROUTE = "$STATE_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val DATA_EQUIP_ROUTE = "$DATA_EQUIP_SCREEN/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}"
    const val DATA_COLAB_ROUTE = "$DATA_COLAB_SCREEN/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}"
    const val PLATE_ROUTE = "$PLATE_SCREEN/{$OPTION_ARG}/{$ID_MAIN_ARG}"
    const val DATA_VEHICLE_ROUTE = "$DATA_VEHICLE_SCREEN/{$ID_MAIN_ARG}"
    const val DATA_INVOLVED_ROUTE = "$DATA_INVOLVED_SCREEN/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val BRAND_ROUTE = "$BRAND_SCREEN/{$OPTION_ARG}/{$ID_MAIN_ARG}"
    const val DOCUMENT_ROUTE = "$DOCUMENT_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val NAME_ROUTE = "$NAME_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val PHONE_ROUTE = "$PHONE_SCREEN/{$OPTION_ARG}/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val ADDRESS_ROUTE = "$ADDRESS_SCREEN/{$FLOW_NOTE_ARG}/{$ID_MAIN_ARG}/{$ID_SECONDARY_ARG}"
    const val INVOLVED_WITNESS_ROUTE = INVOLVED_WITNESS_SCREEN
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
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$EQUIP_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToColab(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$COLAB_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToDataVehicleOwn(
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_OWN_SCREEN/$idMain")
    }

    fun navigateToDetail(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$DETAIL_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToEquipSecList(
        idMain: Int,
    ){
        navController.navigate("$EQUIP_SEC_LIST_SCREEN/$idMain")
    }

    fun navigateToPassengerList(
        flow: Int,
        idMain: Int,
    ){
        navController.navigate("$PASSENGER_LIST_SCREEN/$flow/$idMain")
    }

    fun navigateToState(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ){
        navController.navigate("$STATE_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToDataEquip(
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_EQUIP_SCREEN/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToDataColab(
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_COLAB_SCREEN/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToDataVehicleInvolved(
        idMain: Int,
    ){
        navController.navigate("$DATA_VEHICLE_INVOLVED_SCREEN/$idMain")
    }

    fun navigateToPlate(
        option: Int,
        idMain: Int,
    ){
        navController.navigate("$PLATE_SCREEN/$option/$idMain")
    }

    fun navigateToBrand(
        option: Int,
        idMain: Int,
    ){
        navController.navigate("$BRAND_SCREEN/$option/$idMain")
    }

    fun navigateToDataVehicle(
        idMain: Int,
    ) {
        navController.navigate("$DATA_VEHICLE_SCREEN/$idMain")
    }


    fun navigateToDocument(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DOCUMENT_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToName(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$NAME_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToDataInvolved(
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$DATA_INVOLVED_SCREEN/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToPhone(
        option: Int,
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$PHONE_SCREEN/$option/$flowNote/$idMain/$idSecondary")
    }

    fun navigateToAddress(
        flowNote: Int,
        idMain: Int,
        idSecondary: Int,
    ) {
        navController.navigate("$ADDRESS_SCREEN/$flowNote/$idMain/$idSecondary")
    }

    //////////////////////////////////////////////////////////////////////

    ///////////////////////// Menu Card //////////////////////////////////

    fun navigateToDataInitial() {
        navController.navigate(DATA_INITIAL_SCREEN)
    }

    fun navigateToLocalSupport() {
        navController.navigate(LOCAL_SUPPORT_SCREEN)
    }

    fun navigateToVehicleFull() {
        navController.navigate(VEHICLE_FULL_SCREEN)
    }

    fun navigateToInvolvedWitness() {
        navController.navigate(INVOLVED_WITNESS_SCREEN)
    }

    //////////////////////////////////////////////////////////////////////

}