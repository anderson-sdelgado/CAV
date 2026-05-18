package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.DataLocal

data class DataLocalRetrofitModel(
    val id: Int,
    val idOption: Int,
    val idItem: Int
)

fun DataLocalRetrofitModel.retrofitModelToEntity(): DataLocal {
    return with(this) {
        DataLocal(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}