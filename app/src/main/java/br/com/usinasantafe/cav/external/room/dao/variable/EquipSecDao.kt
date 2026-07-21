package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
import br.com.usinasantafe.cav.lib.TB_CARD
import br.com.usinasantafe.cav.lib.TB_EQUIP_SEC

@Dao
interface EquipSecDao {

    @Insert
    fun insert(model: EquipSecRoomModel): Long

    @Query("SELECT * FROM $TB_EQUIP_SEC")
    fun all(): List<EquipSecRoomModel>

    @Query("SELECT * FROM $TB_EQUIP_SEC WHERE idVehicle IN (:idVehicleList) ORDER BY id asc")
    fun listByIdVehicleList(idVehicleList: List<Int>): List<EquipSecRoomModel>

}