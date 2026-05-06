package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.lib.TB_R_OPTION_ITEM_DATA_LOCAL

@Entity(tableName = TB_R_OPTION_ITEM_DATA_LOCAL)
data class ROptionItemDataLocalRoomModel(
    @PrimaryKey
    val id: Int,
    val idOption: Int,
    val idItem: Int
)

fun ROptionItemDataLocalRoomModel.roomModelToEntity(): ROptionItemDataLocal {
    return with(this){
        ROptionItemDataLocal(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}

fun ROptionItemDataLocal.entityToRoomModel(): ROptionItemDataLocalRoomModel {
    return with(this){
        ROptionItemDataLocalRoomModel(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}
