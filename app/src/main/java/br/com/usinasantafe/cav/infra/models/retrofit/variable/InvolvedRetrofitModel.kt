package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedRoomModel
import br.com.usinasantafe.cav.lib.State

data class InvolvedRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State?,
    val detail: String?
)

fun InvolvedRoomModel.roomModelToRetrofitModel(): InvolvedRetrofitModel {
    return with(this) {
        InvolvedRetrofitModel(
            id = id!!,
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state,
            detail = detail
        )
    }
}
