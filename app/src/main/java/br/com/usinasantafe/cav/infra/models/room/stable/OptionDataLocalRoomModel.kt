package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.lib.TB_OPTION_DATA_LOCAL

@Entity(tableName = TB_OPTION_DATA_LOCAL)
data class OptionDataLocalRoomModel(
    @PrimaryKey
    val id: Int,
    val desc: String
)

fun OptionDataLocalRoomModel.roomModelToEntity(): OptionDataLocal {
    return with(this){
        OptionDataLocal(
            id = id,
            desc = desc
        )
    }
}

fun OptionDataLocal.entityToRoomModel(): OptionDataLocalRoomModel {
    return with(this){
        OptionDataLocalRoomModel(
            id = id,
            desc = desc
        )
    }
}
