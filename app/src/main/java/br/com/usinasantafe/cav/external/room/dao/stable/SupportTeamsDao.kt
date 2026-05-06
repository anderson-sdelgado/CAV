package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.lib.TB_SUPPORT_TEAMS

@Dao
interface SupportTeamsDao {

    @Insert
    fun insertAll(list: List<SupportTeamsRoomModel>)

    @Query("DELETE FROM $TB_SUPPORT_TEAMS")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_SUPPORT_TEAMS")
    suspend fun all(): List<SupportTeamsRoomModel>

}