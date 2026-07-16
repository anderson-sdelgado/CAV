package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.lib.State

data class VehicleOwnRetrofitModel(
    val id: Int,
    val idCard: Int,
    val idEquip: Int,
    val detailEquip: String?,
    val reg: Long,
    val state: State,
    val detailColab: String?
)
