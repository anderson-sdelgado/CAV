package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.EquipCard

data class EquipCardSharedPreferencesModel(
    var id: Int? = null,
    var idEquip: Int? = null,
    var detail: String? = null,
)

fun EquipCardSharedPreferencesModel.sharedPreferencesModelToEntity(): EquipCard {
    return with(this) {
        EquipCard(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}

fun EquipCard.entityToSharedPreferencesModel(): EquipCardSharedPreferencesModel {
    return with(this) {
        EquipCardSharedPreferencesModel(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}