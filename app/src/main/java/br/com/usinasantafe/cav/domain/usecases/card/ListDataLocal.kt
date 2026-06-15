package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListDataLocal {
    suspend operator fun invoke(): Result<List<Pair<String, String>>>
}

class IListDataLocal @Inject constructor(
    private val cardRepository: CardRepository,
    private val dataLocalRepository: DataLocalRepository
): ListDataLocal {

    override suspend fun invoke(): Result<List<Pair<String, String>>> =
        call(getClassAndMethod()) {
            val idList = cardRepository.listIdDataLocal().getOrThrow()
            idList.map {
                val rOptionItem = dataLocalRepository.getROptionItemById(it).getOrThrow()
                val descOption = dataLocalRepository.getDescOptionById(rOptionItem.idOption).getOrThrow()
                val descItem = dataLocalRepository.getDescItemById(rOptionItem.idItem).getOrThrow()
                Pair(descOption, descItem)
            }
        }

}