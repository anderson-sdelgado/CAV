package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel

data class EquipSecRetrofitModel(
    val id: Int,
    val idEquip: Int,
    val detail: String?
)

fun EquipSecRoomModel.roomModelToRetrofitModel(): EquipSecRetrofitModel{
    return with(this) {
        EquipSecRetrofitModel(
            id = id!!,
            idEquip = idEquip,
            detail = detail
        )
    }
}
