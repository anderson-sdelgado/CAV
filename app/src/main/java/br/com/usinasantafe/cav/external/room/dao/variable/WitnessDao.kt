package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel
import br.com.usinasantafe.cav.lib.TB_WITNESS

@Dao
interface WitnessDao {

    @Insert
    fun insert(model: WitnessRoomModel): Long

    @Query("SELECT * FROM $TB_WITNESS")
    fun all(): List<WitnessRoomModel>

    @Query("SELECT * FROM $TB_WITNESS WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<WitnessRoomModel>

    @Query("DELETE FROM $TB_WITNESS WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)

}