package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedRoomModel
import br.com.usinasantafe.cav.lib.TB_EQUIP_SEC
import br.com.usinasantafe.cav.lib.TB_INVOLVED

@Dao
interface InvolvedDao {

    @Insert
    fun insert(model: InvolvedRoomModel): Long

    @Query("SELECT * FROM $TB_INVOLVED")
    fun all(): List<InvolvedRoomModel>

}