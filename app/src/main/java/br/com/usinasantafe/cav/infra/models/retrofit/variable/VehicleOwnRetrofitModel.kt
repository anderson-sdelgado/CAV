package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.lib.State

data class VehicleOwnRetrofitModel(
    val id: Int,
    val idEquip: Int,
    val detailEquip: String?,
    val equipSecList: List<EquipSecRetrofitModel>,
    val reg: Long,
    val state: Int,
    val detailColab: String?,
    val passengerColabList: List<PassengerColabRetrofitModel>
)

fun VehicleOwnRoomModel.roomModelToRetrofitModel(): VehicleOwnRetrofitModel {
    return with(this) {
        VehicleOwnRetrofitModel(
            id = id!!,
            idEquip = idEquip,
            detailEquip = detailEquip,
            equipSecList = emptyList(),
            reg = reg,
            state = state.id,
            detailColab = detailColab,
            passengerColabList = emptyList()
        )
    }
}
