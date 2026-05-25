package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Local

data class LocalSharedPreferencesModel(
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)

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