package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Vehicle

data class VehicleSharedPreferencesModel(
    var id: Int? = null,
    var plate: String? = null,
    var brand: String? = null,
    var detail: String? = null,
)

fun VehicleSharedPreferencesModel.sharedPreferencesModelToEntity(): Vehicle {
    return with(this) {
        Vehicle(
            id = id,
            plate = plate,
            brand = brand,
            detail = detail
        )
    }
}

fun Vehicle.entityToSharedPreferencesModel(): VehicleSharedPreferencesModel{
    return with(this) {
        VehicleSharedPreferencesModel(
            id = id,
            plate = plate,
            brand = brand,
            detail = detail
        )
    }
}
