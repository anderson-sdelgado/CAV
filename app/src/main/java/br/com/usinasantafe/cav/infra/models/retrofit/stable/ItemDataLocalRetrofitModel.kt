package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal

data class ItemDataLocalRetrofitModel(
    val id: Int,
    val desc: String
)

fun ItemDataLocalRetrofitModel.retrofitModelToEntity(): ItemDataLocal {
    return with(this) {
        ItemDataLocal(
            id = id,
            desc = desc
        )
    }
}
