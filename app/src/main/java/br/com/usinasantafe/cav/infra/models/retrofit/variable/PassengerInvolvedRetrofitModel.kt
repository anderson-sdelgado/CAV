package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerInvolvedRoomModel
import br.com.usinasantafe.cav.lib.State

data class PassengerInvolvedRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: Int,
    val detail: String?
)

fun PassengerInvolvedRoomModel.roomModelToRetrofitModel(): PassengerInvolvedRetrofitModel {
    return with(this) {
        PassengerInvolvedRetrofitModel(
            id = id!!,
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state.id,
            detail = detail
        )
    }
}
