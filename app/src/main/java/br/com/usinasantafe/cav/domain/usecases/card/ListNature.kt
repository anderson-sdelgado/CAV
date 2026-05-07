package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListNature {
    suspend operator fun invoke(): Result<List<ItemCheckBoxModel>>
}

class IListNature @Inject constructor(
    private val natureRepository: NatureRepository
): ListNature {

    override suspend fun invoke(): Result<List<ItemCheckBoxModel>> =
        call(getClassAndMethod()) {
            val entityList = natureRepository.listAll().getOrThrow()
            entityList.map {
                ItemCheckBoxModel(
                    id = it.id,
                    desc = it.desc,
                    flag = false
                )
            }
        }

}