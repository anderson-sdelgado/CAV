package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident

data class TypeAccidentRetrofitModel(
    val id: Int,
    val desc: String
)

fun TypeAccidentRetrofitModel.retrofitModelToEntity(): TypeAccident {
    return with(this) {
        TypeAccident(
            id = id,
            desc = desc
        )
    }
}
