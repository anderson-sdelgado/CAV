package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.LocalViewModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetLocal {
    suspend operator fun invoke(): Result<LocalViewModel>
}

class IGetLocal @Inject constructor(
    private val cardRepository: CardRepository
): GetLocal {

    override suspend fun invoke(): Result<LocalViewModel> =
        call(getClassAndMethod()) {
            val entity = cardRepository.getLocal().getOrThrow()
            LocalViewModel(
                address = entity.address ?: "",
                latitude = entity.latitude?.toString() ?: "",
                longitude = entity.longitude?.toString() ?: ""
            )
        }

}