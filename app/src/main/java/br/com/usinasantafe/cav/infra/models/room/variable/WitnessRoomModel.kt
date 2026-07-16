package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.lib.TB_WITNESS
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_WITNESS)
data class WitnessRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val name: String,
    val phone: String,
    val detail: String?
)

fun InvolvedSharedPreferencesModel.sharedPreferencesModelToWitnessRoomModel(
    idCard: Int
): WitnessRoomModel{
    return with(this) {
        WitnessRoomModel(
            idCard = idCard,
            name = ::name.required(),
            phone = ::phone.required(),
            detail = detail
        )
    }
}