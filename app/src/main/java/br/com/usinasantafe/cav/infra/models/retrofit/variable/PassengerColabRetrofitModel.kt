package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
import br.com.usinasantafe.cav.lib.State

data class PassengerColabRetrofitModel(
    val id: Int,
    val reg: Long,
    val state: State,
    val detail: String?,
)

fun PassengerColabRoomModel.roomModelToRetrofitModel(): PassengerColabRetrofitModel {
    return with(this) {
        PassengerColabRetrofitModel(
            id = id!!,
            reg = reg,
            state = state,
            detail = detail
        )
    }
}
