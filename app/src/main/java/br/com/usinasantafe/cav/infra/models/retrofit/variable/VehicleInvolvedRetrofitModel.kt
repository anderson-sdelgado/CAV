package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.lib.State

data class VehicleInvolvedRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State,
    val detailDriver: String?,
    val plate: String,
    val brand: String,
    val detailVehicle: String?,
    val passengerInvolvedList: List<PassengerInvolvedRetrofitModel>
)

fun VehicleInvolvedRoomModel.roomModelToRetrofitModel(): VehicleInvolvedRetrofitModel {
    return with(this) {
        VehicleInvolvedRetrofitModel(
            id = id!!,
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state,
            detailDriver = detailDriver,
            plate = plate,
            brand = brand,
            detailVehicle = detailVehicle,
            passengerInvolvedList = emptyList()
        )
    }
}
