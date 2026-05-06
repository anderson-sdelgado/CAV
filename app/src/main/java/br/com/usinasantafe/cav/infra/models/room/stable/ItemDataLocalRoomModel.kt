package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.lib.TB_ITEM_DATA_LOCAL

@Entity(tableName = TB_ITEM_DATA_LOCAL)
data class ItemDataLocalRoomModel(
    @PrimaryKey
    val id: Int,
    val desc: String
)

fun ItemDataLocalRoomModel.roomModelToEntity(): ItemDataLocal {
    return with(this){
        ItemDataLocal(
            id = id,
            desc = desc
        )
    }
}

fun ItemDataLocal.entityToRoomModel(): ItemDataLocalRoomModel {
    return with(this){
        ItemDataLocalRoomModel(
            id = id,
            desc = desc
        )
    }
}
