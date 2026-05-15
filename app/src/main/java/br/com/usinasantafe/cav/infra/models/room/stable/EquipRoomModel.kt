package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.lib.TB_EQUIP

@Entity(tableName = TB_EQUIP)
data class EquipRoomModel(
    @PrimaryKey
    val id: Int,
    val nro: Long,
    val description: String
)

fun EquipRoomModel.roomModelToEntity(): Equip {
    return with(this){
        Equip(
            id = id,
            nro = nro,
            description = description
        )
    }
}

fun Equip.entityToRoomModel(): EquipRoomModel {
    return with(this){
        EquipRoomModel(
            id = id,
            nro = nro,
            description = description
        )
    }
}

