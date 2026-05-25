package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Card

data class CardSharedPreferencesModel(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: LocalSharedPreferencesModel = LocalSharedPreferencesModel(),
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList()
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
            idSupportTeamsList = idSupportTeamsList
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
            idSupportTeamsList = idSupportTeamsList
        )
    }
}
