package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.*
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
    private val dataLocalRepository: DataLocalRepository,
    private val cardRepository: CardRepository
): SetDataLocalList {

    override suspend fun invoke(
        idOption: Int,
        list: List<ItemCheckBoxScreenModel>
    ): Result<Unit> =
        call(getClassAndMethod()) {
            val dataLocalList = dataLocalRepository.listDataLocalByIdOption(idOption).getOrThrow()
            val idDataLocalNoteList = cardRepository.listIdDataLocal().getOrThrow()
            val idDataLocalDBList = dataLocalList.map { it.id }
            val idDataLocalDBSet = idDataLocalDBList.toSet()
            val idDataLocalCleanList = idDataLocalNoteList.filterNot{ idDataLocalDBSet.contains(it) }
            val idDataLocalCheckList = list.filter { it.flag }.map { it.id }
            val idDataLocalFinishList = idDataLocalCleanList + idDataLocalCheckList
            cardRepository.setIdDataLocalList(idDataLocalFinishList).getOrThrow()
        }

}
