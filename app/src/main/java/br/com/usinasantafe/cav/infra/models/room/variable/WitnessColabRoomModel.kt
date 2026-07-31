package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.TB_INVOLVED_COLAB
import br.com.usinasantafe.cav.lib.TB_WITNESS_COLAB
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_WITNESS_COLAB)
data class WitnessColabRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val reg: Long,
    val detail: String?,
)

fun ColabCardSharedPreferencesModel.sharedPreferencesModelToWitnessColabRoomModel(
    idCard: Int
): WitnessColabRoomModel {
    return with(this) {
        WitnessColabRoomModel(
            idCard = idCard,
            reg = ::reg.required(),
            detail = detail
        )
    }
}
