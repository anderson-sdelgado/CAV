package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDataLocalList {
    suspend operator fun invoke(
        idOption: Int,
        list: List<ItemCheckBoxScreenModel>
    ): Result<Unit>
}

class ISetDataLocalList @Inject constructor(
): SetDataLocalList {

    override suspend fun invoke(
        idOption: Int,
        list: List<ItemCheckBoxScreenModel>
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}