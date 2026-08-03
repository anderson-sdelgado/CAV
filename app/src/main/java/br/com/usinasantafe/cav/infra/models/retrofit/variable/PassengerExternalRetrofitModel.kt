package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.PassengerExternalRoomModel
import br.com.usinasantafe.cav.utils.required

data class PassengerExternalRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: Int,
    val detail: String?
)

fun PassengerExternalRoomModel.roomModelToRetrofitModel(): PassengerExternalRetrofitModel {
    return with(this) {
        PassengerExternalRetrofitModel(
            id = ::id.required(),
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state.id,
            detail = detail
        )
    }
}
