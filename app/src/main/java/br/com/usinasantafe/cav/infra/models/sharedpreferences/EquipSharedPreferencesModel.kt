package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.EquipCard

data class EquipSharedPreferencesModel(
    var id: Int? = null,
    var idEquip: Int? = null,
    var detail: String? = null,
)

fun EquipSharedPreferencesModel.sharedPreferencesModelToEntity(): EquipCard {
    return with(this) {
        EquipCard(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}

fun EquipCard.entityToSharedPreferencesModel(): EquipSharedPreferencesModel {
    return with(this) {
        EquipSharedPreferencesModel(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}