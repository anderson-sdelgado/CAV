package br.com.usinasantafe.cav.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.domain.entities.stable.Colab
import br.com.usinasantafe.cav.lib.TB_COLAB

@Entity(tableName = TB_COLAB)
data class ColabRoomModel (
    @PrimaryKey
    val reg: Long,
    val name: String
)

fun ColabRoomModel.roomModelToEntity(): Colab {
    return with(this){
        Colab(
            reg = this.reg,
            name = this.name,
        )
    }
}

fun Colab.entityToRoomModel(): ColabRoomModel {
    return with(this){
        ColabRoomModel(
            reg = this.reg,
            name = this.name,
        )
    }
}
