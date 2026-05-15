package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListItemDataLocal {
    suspend operator fun invoke(id: Int): Result<List<ItemCheckBoxScreenModel>>
}

class IListItemDataLocal @Inject constructor(
): ListItemDataLocal {

    override suspend fun invoke(id: Int): Result<List<ItemCheckBoxScreenModel>> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}