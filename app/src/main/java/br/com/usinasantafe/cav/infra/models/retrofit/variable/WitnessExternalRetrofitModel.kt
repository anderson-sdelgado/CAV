package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessExternalRoomModel
import br.com.usinasantafe.cav.utils.required

data class WitnessExternalRetrofitModel(
    val id: Int,
    val name: String,
    val phone: String,
    val detail: String?
)

fun WitnessExternalRoomModel.roomModelToRetrofitModel(): WitnessExternalRetrofitModel {
    return with(this) {
        WitnessExternalRetrofitModel(
            id = ::id.required(),
            name = name,
            phone = phone,
            detail = detail
        )
    }
}