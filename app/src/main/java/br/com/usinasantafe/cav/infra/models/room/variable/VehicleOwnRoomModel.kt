package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.lib.TB_VEHICLE_OWN
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_VEHICLE_OWN)
data class VehicleOwnRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val idCard: Int,
    val idEquip: Int,
    val detailEquip: String?,
    val reg: Long,
    val state: State,
    val flagRealizedBreathalyzer: Boolean,
    val flagResultBreathalyzer: Boolean?,
    val countBreathalyzer: Double?,
    val detailColab: String?
)

fun VehicleOwnSharedPreferencesModel.sharedPreferencesModelToInvolvedExternalRoomModel(
    idCard: Int,
): VehicleOwnRoomModel {
    return with(this) {
        VehicleOwnRoomModel(
            idCard = idCard,
            idEquip = equip::idEquip.required(),
            detailEquip = equip.detail,
            reg = colab::reg.required(),
            state = colab::state.required(),
            flagRealizedBreathalyzer = colab::flagRealizedBreathalyzer.required(),
            flagResultBreathalyzer = colab.flagResultBreathalyzer,
            countBreathalyzer = colab.countBreathalyzer,
            detailColab = colab.detail,
        )
    }
}
