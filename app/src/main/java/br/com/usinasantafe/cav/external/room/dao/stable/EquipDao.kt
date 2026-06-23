package br.com.usinasantafe.cav.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.lib.TB_EQUIP

@Dao
interface EquipDao {

    @Insert
    fun insertAll(list: List<EquipRoomModel>)

    @Query("DELETE FROM $TB_EQUIP")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_EQUIP")
    suspend fun all(): List<EquipRoomModel>

    @Query("SELECT EXISTS(SELECT 1 FROM $TB_EQUIP WHERE nro = :nro)")
    suspend fun hasNro(nro: Long): Boolean

    @Query("SELECT id FROM $TB_EQUIP WHERE nro = :nro")
    suspend fun getIdByNro(nro: Long): Int?

    @Query("SELECT nro FROM $TB_EQUIP WHERE id = :id")
    suspend fun getNroById(id: Int): Long?

    @Query("SELECT * FROM $TB_EQUIP WHERE id = :id")
    suspend fun getById(id: Int): EquipRoomModel?

    @Query("SELECT * FROM $TB_EQUIP WHERE id IN (:idList)")
    suspend fun listByIdList(idList: List<Int>): List<EquipRoomModel>
}