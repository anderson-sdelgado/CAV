package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
import br.com.usinasantafe.cav.lib.TB_PASSENGER_COLAB

@Dao
interface PassengerColabDao {

    @Insert
    fun insert(model: PassengerColabRoomModel): Long

    @Query("SELECT * FROM $TB_PASSENGER_COLAB")
    fun all(): List<PassengerColabRoomModel>

    @Query("SELECT * FROM $TB_PASSENGER_COLAB WHERE idVehicle IN (:idVehicleList) ORDER BY id asc")
    fun listByIdVehicleList(idVehicleList: List<Int>): List<PassengerColabRoomModel>

    @Query("DELETE FROM $TB_PASSENGER_COLAB WHERE idVehicle IN (:idVehicleList)")
    fun deleteByIdVehicleList(idVehicleList: List<Int>)

}