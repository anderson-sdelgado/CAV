package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_INVOLVED_COLAB
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_INVOLVED_COLAB)
data class InvolvedColabRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val reg: Long,
    val state: State,
    val detail: String?,
)

fun ColabCardSharedPreferencesModel.sharedPreferencesModelToInvolvedColabRoomModel(
    idCard: Int
): InvolvedColabRoomModel {
    return with(this) {
        InvolvedColabRoomModel(
            idCard = idCard,
            reg = ::reg.required(),
            state = ::state.required(),
            detail = detail
        )
    }
}

