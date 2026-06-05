package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListEquipSec {
    suspend operator fun invoke(id: Int): Result<List<ItemListScreenModel>>
}

class IListEquipSec @Inject constructor(
): ListEquipSec {

    override suspend fun invoke(id: Int): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
