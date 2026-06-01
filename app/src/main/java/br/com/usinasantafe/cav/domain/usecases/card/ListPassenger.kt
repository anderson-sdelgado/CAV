package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListPassenger {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<List<ItemListScreenModel>>
}

class IListPassenger @Inject constructor(
): ListPassenger {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}