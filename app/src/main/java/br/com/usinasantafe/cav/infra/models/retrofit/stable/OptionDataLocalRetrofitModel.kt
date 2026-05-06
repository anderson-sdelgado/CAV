package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal

data class OptionDataLocalRetrofitModel(
    val id: Int,
    val desc: String
)

fun OptionDataLocalRetrofitModel.retrofitModelToEntity(): OptionDataLocal {
    return with(this) {
        OptionDataLocal(
            id = id,
            desc = desc
        )
    }
}