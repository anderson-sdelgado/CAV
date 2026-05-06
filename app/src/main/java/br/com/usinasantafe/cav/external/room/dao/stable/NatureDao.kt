package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.lib.TB_NATURE

@Dao
interface NatureDao {

    @Insert
    fun insertAll(list: List<NatureRoomModel>)

    @Query("DELETE FROM $TB_NATURE")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_NATURE")
    suspend fun all(): List<NatureRoomModel>

}