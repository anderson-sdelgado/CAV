package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessExternalRoomModel
import br.com.usinasantafe.cav.lib.TB_WITNESS_EXTERNAL

@Dao
interface WitnessExternalDao {

    @Insert
    fun insert(model: WitnessExternalRoomModel): Long

    @Query("SELECT * FROM $TB_WITNESS_EXTERNAL")
    fun all(): List<WitnessExternalRoomModel>

    @Query("SELECT * FROM $TB_WITNESS_EXTERNAL WHERE idCard = :idCard ORDER BY id ASC")
    fun listByIdCard(idCard: Int): List<WitnessExternalRoomModel>

    @Query("DELETE FROM $TB_WITNESS_EXTERNAL WHERE idCard = :idCard")
    fun deleteByIdCard(idCard: Int)

}