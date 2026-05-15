package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.lib.TB_ITEM_DATA_LOCAL

@Entity(tableName = TB_ITEM_DATA_LOCAL)
data class ItemDataLocalRoomModel(
    @PrimaryKey
    val id: Int,
    val description: String
)

fun ItemDataLocalRoomModel.roomModelToEntity(): ItemDataLocal {
    return with(this){
        ItemDataLocal(
            id = id,
            description = description
        )
    }
}

fun ItemDataLocal.entityToRoomModel(): ItemDataLocalRoomModel {
    return with(this){
        ItemDataLocalRoomModel(
            id = id,
            description = description
        )
    }
}
