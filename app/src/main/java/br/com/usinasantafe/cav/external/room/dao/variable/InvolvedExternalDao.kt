package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedExternalRoomModel
import br.com.usinasantafe.cav.lib.TB_INVOLVED_EXTERNAL

@Dao
interface InvolvedExternalDao {

    @Insert
    fun insert(model: InvolvedExternalRoomModel): Long

    @Query("SELECT * FROM $TB_INVOLVED_EXTERNAL")
    fun all(): List<InvolvedExternalRoomModel>

    @Query("SELECT * FROM $TB_INVOLVED_EXTERNAL WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<InvolvedExternalRoomModel>

    @Query("DELETE FROM $TB_INVOLVED_EXTERNAL WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)

}