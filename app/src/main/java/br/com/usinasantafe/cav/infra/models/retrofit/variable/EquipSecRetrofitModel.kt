package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
import br.com.usinasantafe.cav.utils.required

data class EquipSecRetrofitModel(
    val id: Int,
    val idEquip: Int,
    val detail: String?
)

fun EquipSecRoomModel.roomModelToRetrofitModel(): EquipSecRetrofitModel{
    return with(this) {
        EquipSecRetrofitModel(
            id = ::id.required(),
            idEquip = idEquip,
            detail = detail
        )
    }
}
