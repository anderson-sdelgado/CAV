package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.required

data class VehicleOwnRetrofitModel(
    val id: Int,
    val idEquip: Int,
    val detailEquip: String?,
    val equipSecList: List<EquipSecRetrofitModel>,
    val reg: Long,
    val state: Int,
    val flagRealizedBreathalyzer: Int,
    val flagResultBreathalyzer: Int?,
    val countBreathalyzer: Double?,
    val detailColab: String?,
    val passengerColabList: List<PassengerColabRetrofitModel>
)

fun VehicleOwnRoomModel.roomModelToRetrofitModel(): VehicleOwnRetrofitModel {
    return with(this) {
        VehicleOwnRetrofitModel(
            id = ::id.required(),
            idEquip = idEquip,
            detailEquip = detailEquip,
            equipSecList = emptyList(),
            reg = reg,
            state = state.id,
            flagRealizedBreathalyzer = if (flagRealizedBreathalyzer) 1 else 0,
            flagResultBreathalyzer = flagResultBreathalyzer?.let { if (it) 1 else 0 },
            countBreathalyzer = countBreathalyzer,
            detailColab = detailColab,
            passengerColabList = emptyList()
        )
    }
}
