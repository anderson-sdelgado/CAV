package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.lib.TB_VEHICLE_INVOLVED
import br.com.usinasantafe.cav.lib.TB_VEHICLE_OWN

@Dao
interface VehicleOwnDao {

    @Insert
    fun insert(model: VehicleOwnRoomModel): Long

    @Query("SELECT * FROM $TB_VEHICLE_OWN")
    fun all(): List<VehicleOwnRoomModel>

}