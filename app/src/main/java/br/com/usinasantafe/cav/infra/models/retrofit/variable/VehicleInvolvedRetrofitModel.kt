package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.lib.State

data class VehicleInvolvedRetrofitModel(
    val id: Int,
    val idCard: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State,
    val detailDriver: String?,
    val plate: String,
    val brand: String,
    val detailVehicle: String?
)
