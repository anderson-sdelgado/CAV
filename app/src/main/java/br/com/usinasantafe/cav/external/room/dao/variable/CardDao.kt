package br.com.usinasantafe.cav.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.lib.TB_CARD
import java.util.Date

@Dao
interface CardDao {

    @Insert
    fun insert(model: CardRoomModel): Long

    @Query("UPDATE $TB_CARD SET statusSend = :statusSend, idServ = :idServ WHERE id = :id")
    fun update(id: Int, idServ: Int, statusSend: StatusSend)

    @Query("DELETE FROM $TB_CARD WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM $TB_CARD")
    fun all(): List<CardRoomModel>

    @Query("SELECT EXISTS(SELECT * FROM $TB_CARD WHERE statusSend = :statusSend)")
    fun hasSend(statusSend: StatusSend): Boolean

    @Query("""
        SELECT * FROM $TB_CARD
        WHERE statusSend = :statusSend
        ORDER BY dateHour ASC
        LIMIT 1
    """)
    fun oldest(statusSend: StatusSend): CardRoomModel

    @Query("SELECT * FROM $TB_CARD WHERE statusSend = :statusSend AND dateHour < :dateHour")
    fun listDelete(statusSend: StatusSend, dateHour: Date): List<CardRoomModel>

}