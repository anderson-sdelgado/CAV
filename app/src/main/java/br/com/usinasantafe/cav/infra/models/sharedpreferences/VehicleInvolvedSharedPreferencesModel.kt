package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved

data class VehicleInvolvedSharedPreferencesModel(
    var id: Int? = null,
    var vehicle: VehicleSharedPreferencesModel = VehicleSharedPreferencesModel(),
    var driver: InvolvedSharedPreferencesModel = InvolvedSharedPreferencesModel(),
    var passengerInvolvedList: List<InvolvedSharedPreferencesModel> = emptyList()
)

fun VehicleInvolvedSharedPreferencesModel.sharedPreferencesModelToEntity(): VehicleInvolved {
    return with(this) {
        VehicleInvolved(
            id = id,
            vehicle = vehicle.sharedPreferencesModelToEntity(),
            driver = driver.sharedPreferencesModelToEntity(),
            passengerInvolvedList = passengerInvolvedList.map { it.sharedPreferencesModelToEntity() }
        )
    }
}

fun VehicleInvolved.entityToSharedPreferencesModel(): VehicleInvolvedSharedPreferencesModel {
    return with(this) {
        VehicleInvolvedSharedPreferencesModel(
            id = id,
            vehicle = vehicle.entityToSharedPreferencesModel(),
            driver = driver.entityToSharedPreferencesModel(),
            passengerInvolvedList = passengerInvolvedList.map { it.entityToSharedPreferencesModel() }
        )
    }
}