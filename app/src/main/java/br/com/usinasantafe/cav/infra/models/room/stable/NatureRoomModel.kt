package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.lib.TB_NATURE

@Entity(tableName = TB_NATURE)
data class NatureRoomModel(
    @PrimaryKey
    val id: Int,
    val description: String
)

fun NatureRoomModel.roomModelToEntity(): Nature {
    return with(this){
        Nature(
            id = id,
            description = description
        )
    }
}

fun Nature.entityToRoomModel(): NatureRoomModel {
    return with(this){
        NatureRoomModel(
            id = id,
            description = description
        )
    }
}
