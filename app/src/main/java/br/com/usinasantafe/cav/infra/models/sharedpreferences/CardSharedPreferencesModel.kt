package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Card
import br.com.usinasantafe.cav.domain.entities.variable.Local

data class CardSharedPreferencesModel(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: LocalSharedPreferencesModel = LocalSharedPreferencesModel(),
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList()
)

data class LocalSharedPreferencesModel(
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
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

fun LocalSharedPreferencesModel.sharedPreferencesModelToEntity(): Local {
    return with(this) {
        Local(
            address = address,
            latitude = latitude,
            longitude = longitude
        )
    }
}

fun Local.entityToSharedPreferencesModel(): LocalSharedPreferencesModel {
    return with(this) {
        LocalSharedPreferencesModel(
            address = address,
            latitude = latitude,
            longitude = longitude
        )
    }
}