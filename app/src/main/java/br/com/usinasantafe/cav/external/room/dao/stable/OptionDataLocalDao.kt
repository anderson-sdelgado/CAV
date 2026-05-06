package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.lib.TB_OPTION_DATA_LOCAL

@Dao
interface OptionDataLocalDao {

    @Insert
    fun insertAll(list: List<OptionDataLocalRoomModel>)

    @Query("DELETE FROM $TB_OPTION_DATA_LOCAL")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_OPTION_DATA_LOCAL")
    suspend fun all(): List<OptionDataLocalRoomModel>

}