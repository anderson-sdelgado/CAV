package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.lib.TB_CARD

@Dao
interface CardDao {

    @Insert
    fun insert(model: CardRoomModel): Long

    @Query("SELECT * FROM $TB_CARD")
    fun all(): List<CardRoomModel>

}