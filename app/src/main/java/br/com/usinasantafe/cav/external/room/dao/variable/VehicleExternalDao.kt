package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleExternalRoomModel
import br.com.usinasantafe.cav.lib.TB_VEHICLE_INVOLVED

@Dao
interface VehicleExternalDao {

    @Insert
    fun insert(model: VehicleExternalRoomModel): Long

    @Query("SELECT * FROM $TB_VEHICLE_INVOLVED")
    fun all(): List<VehicleExternalRoomModel>

    @Query("SELECT * FROM $TB_VEHICLE_INVOLVED WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<VehicleExternalRoomModel>

    @Query("DELETE FROM $TB_VEHICLE_INVOLVED WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)
}