package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal

data class VehicleExternalSharedPreferencesModel(
    var id: Int? = null,
    var vehicle: VehicleSharedPreferencesModel = VehicleSharedPreferencesModel(),
    var driver: PeopleExternalSharedPreferencesModel = PeopleExternalSharedPreferencesModel(),
    var passengerInvolvedList: List<PeopleExternalSharedPreferencesModel> = emptyList()
)

fun VehicleExternalSharedPreferencesModel.sharedPreferencesModelToEntity(): VehicleExternal {
    return with(this) {
        VehicleExternal(
            id = id,
            vehicle = vehicle.sharedPreferencesModelToEntity(),
            driver = driver.sharedPreferencesModelToEntity(),
            passengerPeopleExternalList = passengerInvolvedList.map { it.sharedPreferencesModelToEntity() }
        )
    }
}

fun VehicleExternal.entityToSharedPreferencesModel(): VehicleExternalSharedPreferencesModel {
    return with(this) {
        VehicleExternalSharedPreferencesModel(
            id = id,
            vehicle = vehicle.entityToSharedPreferencesModel(),
            driver = driver.entityToSharedPreferencesModel(),
            passengerInvolvedList = passengerPeopleExternalList.map { it.entityToSharedPreferencesModel() }
        )
    }
}