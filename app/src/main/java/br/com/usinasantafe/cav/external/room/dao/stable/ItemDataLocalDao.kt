package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.lib.TB_ITEM_DATA_LOCAL

@Dao
interface ItemDataLocalDao {

    @Insert
    fun insertAll(list: List<ItemDataLocalRoomModel>)

    @Query("DELETE FROM $TB_ITEM_DATA_LOCAL")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_ITEM_DATA_LOCAL")
    suspend fun all(): List<ItemDataLocalRoomModel>

}