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
            equipCard = equip.sharedPreferencesModelToEntity(),
            equipCardSecList = equipSecList.map { it.sharedPreferencesModelToEntity() },
            colabCard = colab.sharedPreferencesModelToEntity(),
            passengerColabCardList = passengerColabList.map { it.sharedPreferencesModelToEntity() }
        )
    }
}

fun VehicleOwn.entityToSharedPreferencesModel(): VehicleOwnSharedPreferencesModel {
    return with(this) {
        VehicleOwnSharedPreferencesModel(
            id = id,
            equip = equipCard.entityToSharedPreferencesModel(),
            equipSecList = equipCardSecList.map{ it.entityToSharedPreferencesModel() },
            colab = colabCard.entityToSharedPreferencesModel(),
            passengerColabList = passengerColabCardList.map { it.entityToSharedPreferencesModel() }
        )
    }
}