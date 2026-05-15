package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListOptionDataLocal {
    suspend operator fun invoke(): Result<List<ItemListScreenModel>>
}

class IListOptionDataLocal @Inject constructor(
    private val dataLocalRepository: DataLocalRepository
): ListOptionDataLocal {

    override suspend fun invoke(): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            val list = dataLocalRepository.listAllOption().getOrThrow()
            list.map {
                ItemListScreenModel(
                    id = it.id,
                    description = it.description
                )
            }
        }

}