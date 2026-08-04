package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_PASSENGER_COLAB
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_PASSENGER_COLAB)
data class PassengerColabRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idVehicle: Int,
    val reg: Long,
    val state: State,
    val detail: String?,
)

fun ColabCardSharedPreferencesModel.sharedPreferencesModelToInvolvedExternalRoomModel(
    idVehicle: Int
): PassengerColabRoomModel {
    return with(this) {
        PassengerColabRoomModel(
            idVehicle = idVehicle,
            reg = ::reg.required(),
            state = ::state.required(),
            detail = detail
        )
    }
}