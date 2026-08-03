package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.infra.models.room.variable.VehicleExternalRoomModel
import br.com.usinasantafe.cav.utils.required

data class VehicleExternalRetrofitModel(
    val id: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: Int,
    val detailDriver: String?,
    val plate: String,
    val brand: String,
    val detailVehicle: String?,
    val passengerExternalList: List<PassengerExternalRetrofitModel>
)

fun VehicleExternalRoomModel.roomModelToRetrofitModel(): VehicleExternalRetrofitModel {
    return with(this) {
        VehicleExternalRetrofitModel(
            id = ::id.required(),
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state.id,
            detailDriver = detailDriver,
            plate = plate,
            brand = brand,
            detailVehicle = detailVehicle,
            passengerExternalList = emptyList()
        )
    }
}
