package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.TB_EQUIP_SEC
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_EQUIP_SEC)
data class EquipSecRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idVehicle: Int,
    val idEquip: Int,
    val detail: String?
)

fun EquipCardSharedPreferencesModel.sharedPreferencesModelToInvolvedExternalRoomModel(
    idVehicle: Int,
): EquipSecRoomModel{
    return with(this) {
        EquipSecRoomModel(
            idVehicle = idVehicle,
            idEquip = ::idEquip.required(),
            detail = detail
        )
    }
}