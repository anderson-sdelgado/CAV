package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.Equip

data class EquipRetrofitModel(
    val id: Int,
    val nro: Long,
    val desc: String
)

fun EquipRetrofitModel.retrofitModelToEntity(): Equip {
    return with(this) {
        Equip(
            id = id,
            nro = nro,
            desc = desc
        )
    }
}
