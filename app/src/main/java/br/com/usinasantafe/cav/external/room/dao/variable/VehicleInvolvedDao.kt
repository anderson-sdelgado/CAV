package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.lib.TB_VEHICLE_INVOLVED

@Dao
interface VehicleInvolvedDao {

    @Insert
    fun insert(model: VehicleInvolvedRoomModel): Long

    @Query("SELECT * FROM $TB_VEHICLE_INVOLVED")
    fun all(): List<VehicleInvolvedRoomModel>

}