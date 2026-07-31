package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.TB_WITNESS_EXTERNAL
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_WITNESS_EXTERNAL)
data class WitnessExternalRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val name: String,
    val phone: String,
    val detail: String?
)

fun PeopleExternalSharedPreferencesModel.sharedPreferencesModelToWitnessExternalRoomModel(
    idCard: Int
): WitnessExternalRoomModel{
    return with(this) {
        WitnessExternalRoomModel(
            idCard = idCard,
            name = ::name.required(),
            phone = ::phone.required(),
            detail = detail
        )
    }
}