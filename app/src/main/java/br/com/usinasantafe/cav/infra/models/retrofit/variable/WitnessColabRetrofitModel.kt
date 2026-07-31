package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessColabRoomModel
import br.com.usinasantafe.cav.utils.required

data class WitnessColabRetrofitModel(
    val id: Int,
    val reg: Long,
    val detail: String?,
)

fun WitnessColabRoomModel.roomModelToRetrofitModel(): WitnessColabRetrofitModel {
    return with(this) {
        WitnessColabRetrofitModel(
            id = ::id.required(),
            reg = reg,
            detail = detail
        )
    }
}
