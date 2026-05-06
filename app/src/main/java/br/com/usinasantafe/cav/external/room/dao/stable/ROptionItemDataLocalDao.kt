package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.lib.TB_R_OPTION_ITEM_DATA_LOCAL

@Dao
interface ROptionItemDataLocalDao {

    @Insert
    fun insertAll(list: List<ROptionItemDataLocalRoomModel>)

    @Query("DELETE FROM $TB_R_OPTION_ITEM_DATA_LOCAL")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_R_OPTION_ITEM_DATA_LOCAL")
    suspend fun all(): List<ROptionItemDataLocalRoomModel>

}