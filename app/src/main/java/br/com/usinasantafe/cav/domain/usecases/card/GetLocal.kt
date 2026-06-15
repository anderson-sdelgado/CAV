package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.presenter.model.LocalScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetLocal {
    suspend operator fun invoke(): Result<LocalScreenModel>
}

class IGetLocal @Inject constructor(
    private val cardRepository: CardRepository
): GetLocal {

    override suspend fun invoke(): Result<LocalScreenModel> =
        call(getClassAndMethod()) {
            val entity = cardRepository.getLocal().getOrThrow()
            LocalScreenModel(
                address = entity.address ?: "",
                latitude = entity.latitude?.toString() ?: "",
                longitude = entity.longitude?.toString() ?: ""
            )
        }

}
