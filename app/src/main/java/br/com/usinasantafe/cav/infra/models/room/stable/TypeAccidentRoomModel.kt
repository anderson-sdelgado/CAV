package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.lib.TB_TYPE_ACCIDENT

@Entity(tableName = TB_TYPE_ACCIDENT)
data class TypeAccidentRoomModel(
    @PrimaryKey
    val id: Int,
    val description: String
)

fun TypeAccidentRoomModel.roomModelToEntity(): TypeAccident {
    return with(this){
        TypeAccident(
            id = id,
            description = description
        )
    }
}

fun TypeAccident.entityToRoomModel(): TypeAccidentRoomModel {
    return with(this){
        TypeAccidentRoomModel(
            id = id,
            description = description
        )
    }
}