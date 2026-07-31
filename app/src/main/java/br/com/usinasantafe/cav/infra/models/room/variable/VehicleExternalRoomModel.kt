package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_VEHICLE_INVOLVED
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_VEHICLE_INVOLVED)
data class VehicleExternalRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State,
    val detailDriver: String?,
    val plate: String,
    val brand: String,
    var detailVehicle: String?,
)

fun VehicleExternalSharedPreferencesModel.sharedPreferencesModelToInvolvedExternalRoomModel(
    idCard: Int
): VehicleExternalRoomModel {
    return with(this) {
        VehicleExternalRoomModel(
            idCard = idCard,
            document = driver.document,
            name = driver::name.required(),
            phone = driver::phone.required(),
            address = driver.address,
            state = driver::state.required(),
            detailDriver = driver.detail,
            plate = vehicle::plate.required(),
            brand = vehicle::brand.required(),
            detailVehicle = vehicle.detail,
        )
    }
}