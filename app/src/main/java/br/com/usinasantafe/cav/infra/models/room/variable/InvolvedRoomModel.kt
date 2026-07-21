package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_INVOLVED
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_INVOLVED)
data class InvolvedRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val document: String?,
    val name: String,
    val phone: String,
    val address: String?,
    val state: State,
    val detail: String?
)

fun InvolvedSharedPreferencesModel.sharedPreferencesModelToInvolvedRoomModel(
    idCard: Int
): InvolvedRoomModel{
    return with(this) {
        InvolvedRoomModel(
            idCard = idCard,
            document = document,
            name = ::name.required(),
            phone = ::phone.required(),
            address = address,
            state = ::state.required(),
            detail = detail
        )
    }
}
