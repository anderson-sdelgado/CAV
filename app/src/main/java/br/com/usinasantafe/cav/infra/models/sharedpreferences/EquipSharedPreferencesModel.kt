package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Equip

data class EquipSharedPreferencesModel(
    var id: Int? = null,
    var idEquip: Int? = null,
    var detail: String? = null,
)

fun EquipSharedPreferencesModel.sharedPreferencesModelToEntity(): Equip {
    return with(this) {
        Equip(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}

fun Equip.entityToSharedPreferencesModel(): EquipSharedPreferencesModel {
    return with(this) {
        EquipSharedPreferencesModel(
            id = id,
            idEquip = idEquip,
            detail = detail
        )
    }
}