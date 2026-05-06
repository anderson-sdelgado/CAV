package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal

data class ROptionItemDataLocalRetrofitModel(
    val id: Int,
    val idOption: Int,
    val idItem: Int
)

fun ROptionItemDataLocalRetrofitModel.retrofitModelToEntity(): ROptionItemDataLocal {
    return with(this) {
        ROptionItemDataLocal(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}