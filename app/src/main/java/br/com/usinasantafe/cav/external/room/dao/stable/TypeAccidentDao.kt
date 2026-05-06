package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.lib.TB_TYPE_ACCIDENT

@Dao
interface TypeAccidentDao {

    @Insert
    fun insertAll(list: List<TypeAccidentRoomModel>)

    @Query("DELETE FROM $TB_TYPE_ACCIDENT")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_TYPE_ACCIDENT")
    suspend fun all(): List<TypeAccidentRoomModel>

}