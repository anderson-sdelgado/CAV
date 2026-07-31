package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerExternalRoomModel
import br.com.usinasantafe.cav.lib.TB_PASSENGER_INVOLVED

@Dao
interface PassengerExternalDao {

    @Insert
    fun insert(model: PassengerExternalRoomModel): Long

    @Query("SELECT * FROM $TB_PASSENGER_INVOLVED")
    fun all(): List<PassengerExternalRoomModel>

    @Query("SELECT * FROM $TB_PASSENGER_INVOLVED WHERE idVehicle IN (:idVehicleList) ORDER BY id asc")
    fun listByIdVehicleList(idVehicleList: List<Int>): List<PassengerExternalRoomModel>

    @Query("DELETE FROM $TB_PASSENGER_INVOLVED WHERE idVehicle IN (:idVehicleList)")
    fun deleteByIdVehicleList(idVehicleList: List<Int>)

}