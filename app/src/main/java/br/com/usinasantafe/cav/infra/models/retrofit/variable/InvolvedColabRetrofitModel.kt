package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedColabRoomModel
import br.com.usinasantafe.cav.utils.required

data class ColabRetrofitModel(
    val id: Int,
    val reg: Long,
    val state: Int,
    val detail: String?,
)

fun InvolvedColabRoomModel.roomModelToRetrofitModel(): ColabRetrofitModel {
    return with(this) {
        ColabRetrofitModel(
            id = ::id.required(),
            reg = reg,
            state = state.id,
            detail = detail
        )
    }
}
