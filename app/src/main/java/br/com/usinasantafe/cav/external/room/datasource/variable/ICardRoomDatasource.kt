package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.CardDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.dateOneWeekAgo
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import java.util.Date
import javax.inject.Inject

class ICardRoomDatasource @Inject constructor(
   private val cardDao: CardDao
): CardRoomDatasource {

   override suspend fun add(model: CardRoomModel): Result<Int> =
      result(getClassAndMethod()) {
         cardDao.insert(model).toInt()
      }

   override suspend fun update(id: Int, idServ: Int): EmptyResult =
      result(getClassAndMethod()) {
         cardDao.update(id, idServ, StatusSend.SENT)
      }

   override suspend fun hasSend(): Result<Boolean> =
      result(getClassAndMethod()) {
         cardDao.hasSend(StatusSend.SEND)
      }

   override suspend fun getSend(): Result<CardRoomModel> =
      result(getClassAndMethod()) {
          cardDao.oldest()
      }

   override suspend fun listDelete(): Result<List<CardRoomModel>> =
      result(getClassAndMethod()) {
         cardDao.listDelete(StatusSend.SENT, dateOneWeekAgo())
      }

   override suspend fun deleteById(id: Int): EmptyResult =
      result(getClassAndMethod()) {
         cardDao.deleteById(id)
      }

}