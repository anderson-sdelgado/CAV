package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedColabRoomModel
import br.com.usinasantafe.cav.lib.TB_INVOLVED_COLAB

@Dao
interface InvolvedColabDao {

    @Insert
    fun insert(model: InvolvedColabRoomModel): Long

    @Query("SELECT * FROM $TB_INVOLVED_COLAB")
    fun all(): List<InvolvedColabRoomModel>

    @Query("SELECT * FROM $TB_INVOLVED_COLAB WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<InvolvedColabRoomModel>

    @Query("DELETE FROM $TB_INVOLVED_COLAB WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)

}