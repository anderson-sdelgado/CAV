package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetListNature {
    suspend operator fun invoke(list: List<ItemCheckBoxModel>): Result<Unit>
}

class ISetListNature @Inject constructor(
): SetListNature {

    override suspend fun invoke(list: List<ItemCheckBoxModel>): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}