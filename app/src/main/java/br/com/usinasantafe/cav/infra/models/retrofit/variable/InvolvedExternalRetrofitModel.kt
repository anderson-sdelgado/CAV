package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedExternalRoomModel
import br.com.usinasantafe.cav.utils.required

data class InvolvedExternalRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: Int,
    val detail: String?
)

fun InvolvedExternalRoomModel.roomModelToRetrofitModel(): InvolvedExternalRetrofitModel {
    return with(this) {
        InvolvedExternalRetrofitModel(
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
