package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.lib.TB_ITEM_DATA_LOCAL
import br.com.usinasantafe.cav.lib.TB_SUPPORT_TEAMS

@Entity(tableName = TB_SUPPORT_TEAMS)
data class SupportTeamsRoomModel(
    @PrimaryKey
    val id: Int,
    val desc: String
)

fun SupportTeamsRoomModel.roomModelToEntity(): SupportTeams {
    return with(this){
        SupportTeams(
            id = id,
            desc = desc
        )
    }
}

fun SupportTeams.entityToRoomModel(): SupportTeamsRoomModel {
    return with(this){
        SupportTeamsRoomModel(
            id = id,
            desc = desc
        )
    }
}
