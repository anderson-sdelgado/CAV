package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel

data class WitnessRetrofitModel(
    val id: Int,
    val name: String,
    val phone: String,
    val detail: String?
)

fun WitnessRoomModel.roomModelToRetrofitModel(): WitnessRetrofitModel {
    return with(this) {
        WitnessRetrofitModel(
            id = id!!,
            name = name,
            phone = phone,
            detail = detail
        )
    }
}