package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn

data class VehicleOwnSharedPreferencesModel(
    var id: Int? = null,
    var equip: EquipSharedPreferencesModel = EquipSharedPreferencesModel(),
    var equipSecList: List<EquipSharedPreferencesModel> = emptyList(),
    var colab: ColabSharedPreferencesModel = ColabSharedPreferencesModel(),
    var passengerColabList: List<ColabSharedPreferencesModel> = emptyList()
)

fun VehicleOwnSharedPreferencesModel.sharedPreferencesModelToEntity(): VehicleOwn {
    return with(this) {
        VehicleOwn(
            id = id,
            equip = equip.sharedPreferencesModelToEntity(),
            equipSecList = equipSecList.map { it.sharedPreferencesModelToEntity() },
            colab = colab.sharedPreferencesModelToEntity(),
            passengerColabList = passengerColabList.map { it.sharedPreferencesModelToEntity() }
        )
    }
}

fun VehicleOwn.entityToSharedPreferencesModel(): VehicleOwnSharedPreferencesModel {
    return with(this) {
        VehicleOwnSharedPreferencesModel(
            id = id,
            equip = equip.entityToSharedPreferencesModel(),
            equipSecList = equipSecList.map{ it.entityToSharedPreferencesModel() },
            colab = colab.entityToSharedPreferencesModel(),
            passengerColabList = passengerColabList.map { it.entityToSharedPreferencesModel() }
        )
    }
}