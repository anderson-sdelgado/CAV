package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessColabRoomModel
import br.com.usinasantafe.cav.lib.TB_WITNESS_COLAB

@Dao
interface WitnessColabDao {

    @Insert
    fun insert(model: WitnessColabRoomModel): Long

    @Query("SELECT * FROM $TB_WITNESS_COLAB")
    fun all(): List<WitnessColabRoomModel>

    @Query("SELECT * FROM $TB_WITNESS_COLAB WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<WitnessColabRoomModel>

    @Query("DELETE FROM $TB_WITNESS_COLAB WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)

}