package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.lib.TB_DATA_LOCAL

@Dao
interface DataLocalDao {

    @Insert
    fun insertAll(list: List<DataLocalRoomModel>)

    @Query("DELETE FROM $TB_DATA_LOCAL")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_DATA_LOCAL")
    suspend fun all(): List<DataLocalRoomModel>

    @Query("SELECT * FROM $TB_DATA_LOCAL WHERE id = :id")
    suspend fun getById(id: Int): DataLocalRoomModel?

    @Query("SELECT * FROM $TB_DATA_LOCAL WHERE idOption = :id")
    suspend fun listByIdOption(id: Int): List<DataLocalRoomModel>

}