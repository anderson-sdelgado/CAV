package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Card

data class CardSharedPreferencesModel(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: LocalSharedPreferencesModel = LocalSharedPreferencesModel(),
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList(),
    var vehicleOwnList: List<VehicleOwnSharedPreferencesModel> = emptyList(),
    var vehicleInvolvedList: List<VehicleInvolvedSharedPreferencesModel> = emptyList(),
    var involvedList: List<InvolvedSharedPreferencesModel> = emptyList(),
    var witnessList: List<InvolvedSharedPreferencesModel> = emptyList(),
    var obs: String? = null,
)


fun CardSharedPreferencesModel.sharedPreferencesModelToEntity(): Card {
    return with(this) {
        Card(
            regAttendant = regAttendant,
            idCar = idCar,
            local = local.sharedPreferencesModelToEntity(),
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            vehicleOwnList = vehicleOwnList.map { it.sharedPreferencesModelToEntity() },
            vehicleInvolvedList = vehicleInvolvedList.map { it.sharedPreferencesModelToEntity() },
            involvedList = involvedList.map { it.sharedPreferencesModelToEntity() },
            witnessList = witnessList.map { it.sharedPreferencesModelToEntity() },
            obs = obs
        )
    }
}
fun Card.entityToSharedPreferencesModel(): CardSharedPreferencesModel {
    return with(this) {
        CardSharedPreferencesModel(
            regAttendant = regAttendant,
            idCar = idCar,
            local = local.entityToSharedPreferencesModel(),
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            vehicleOwnList = vehicleOwnList.map { it.entityToSharedPreferencesModel() },
            vehicleInvolvedList = vehicleInvolvedList.map { it.entityToSharedPreferencesModel() },
            involvedList = involvedList.map { it.entityToSharedPreferencesModel() },
            witnessList = witnessList.map { it.entityToSharedPreferencesModel() },
            obs = obs
        )
    }
}
