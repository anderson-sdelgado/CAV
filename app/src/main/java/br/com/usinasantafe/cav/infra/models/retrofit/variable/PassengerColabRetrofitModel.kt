package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.required

data class PassengerColabRetrofitModel(
    val id: Int,
    val reg: Long,
    val state: Int,
    val detail: String?,
)

fun PassengerColabRoomModel.roomModelToRetrofitModel(): PassengerColabRetrofitModel {
    return with(this) {
        PassengerColabRetrofitModel(
            id = ::id.required(),
            reg = reg,
            state = state.id,
            detail = detail
        )
    }
}
