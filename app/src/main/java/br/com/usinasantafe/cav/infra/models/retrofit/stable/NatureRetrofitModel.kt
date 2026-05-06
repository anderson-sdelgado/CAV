package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.Nature

data class NatureRetrofitModel(
    val id: Int,
    val desc: String
)

fun NatureRetrofitModel.retrofitModelToEntity(): Nature {
    return with(this) {
        Nature(
            id = id,
            desc = desc
        )
    }
}