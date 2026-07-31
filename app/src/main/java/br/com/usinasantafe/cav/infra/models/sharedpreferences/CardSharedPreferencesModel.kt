package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Card

data class CardSharedPreferencesModel(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: LocalSharedPreferencesModel? = null,
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList(),
    var vehicleOwnList: List<VehicleOwnSharedPreferencesModel> = emptyList(),
    var vehicleExternalList: List<VehicleExternalSharedPreferencesModel> = emptyList(),
    var involvedExternalList: List<PeopleExternalSharedPreferencesModel> = emptyList(),
    var witnessExternalList: List<PeopleExternalSharedPreferencesModel> = emptyList(),
    var involvedColabList: List<ColabCardSharedPreferencesModel> = emptyList(),
    var witnessColabList: List<ColabCardSharedPreferencesModel> = emptyList(),
    var urlPhotoList: List<String> = emptyList(),
    var obs: String? = null,
)


fun CardSharedPreferencesModel.sharedPreferencesModelToEntity(): Card {
    return with(this) {
        Card(
            regAttendant = regAttendant,
            idCar = idCar,
            local = local?.sharedPreferencesModelToEntity(),
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            vehicleOwnList = vehicleOwnList.map { it.sharedPreferencesModelToEntity() },
            vehicleExternalList = vehicleExternalList.map { it.sharedPreferencesModelToEntity() },
            involvedExternalList = involvedExternalList.map { it.sharedPreferencesModelToEntity() },
            witnessExternalList = witnessExternalList.map { it.sharedPreferencesModelToEntity() },
            involvedColabList = involvedColabList.map { it.sharedPreferencesModelToEntity() },
            witnessColabList = witnessColabList.map { it.sharedPreferencesModelToEntity() },
            photoList = urlPhotoList,
            obs = obs
        )
    }
}
fun Card.entityToSharedPreferencesModel(): CardSharedPreferencesModel {
    return with(this) {
        CardSharedPreferencesModel(
            regAttendant = regAttendant,
            idCar = idCar,
            local = local?.entityToSharedPreferencesModel(),
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            vehicleOwnList = vehicleOwnList.map { it.entityToSharedPreferencesModel() },
            vehicleExternalList = vehicleExternalList.map { it.entityToSharedPreferencesModel() },
            involvedExternalList = involvedExternalList.map { it.entityToSharedPreferencesModel() },
            witnessExternalList = witnessExternalList.map { it.entityToSharedPreferencesModel() },
            involvedColabList = involvedColabList.map { it.entityToSharedPreferencesModel() },
            witnessColabList = witnessColabList.map { it.entityToSharedPreferencesModel() },
            urlPhotoList = photoList,
            obs = obs
        )
    }
}
