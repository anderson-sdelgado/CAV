package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.DataLocal
import br.com.usinasantafe.cav.lib.TB_DATA_LOCAL

@Entity(tableName = TB_DATA_LOCAL)
data class DataLocalRoomModel(
    @PrimaryKey
    val id: Int,
    val idOption: Int,
    val idItem: Int
)

fun DataLocalRoomModel.roomModelToEntity(): DataLocal {
    return with(this){
        DataLocal(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}

fun DataLocal.entityToRoomModel(): DataLocalRoomModel {
    return with(this){
        DataLocalRoomModel(
            id = id,
            idOption = idOption,
            idItem = idItem
        )
    }
}
