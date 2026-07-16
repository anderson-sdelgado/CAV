package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_PASSENGER_INVOLVED
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_PASSENGER_INVOLVED)
data class PassengerInvolvedRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idVehicle: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State?,
    val detail: String?
)

fun InvolvedSharedPreferencesModel.sharedPreferencesModelToPassengerInvolvedRoomModel(
    idVehicle: Int,
): PassengerInvolvedRoomModel{
    return with(this) {
        PassengerInvolvedRoomModel(
            idVehicle = idVehicle,
            document = document,
            name = ::name.required(),
            phone = ::phone.required(),
            address = address,
            state = state,
            detail = detail
        )
    }
}
