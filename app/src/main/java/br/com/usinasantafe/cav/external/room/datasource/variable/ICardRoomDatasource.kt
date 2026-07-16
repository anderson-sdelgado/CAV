package br.com.usinasantafe.cav.external.room.datasource.variable

import br.com.usinasantafe.cav.external.room.dao.variable.CardDao
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class ICardRoomDatasource @Inject constructor(
   private val cardDao: CardDao
): CardRoomDatasource {

   override suspend fun add(model: CardRoomModel): Result<Int> =
      result(getClassAndMethod()) {
         cardDao.insert(model).toInt()
      }

}