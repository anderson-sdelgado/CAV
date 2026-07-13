package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetObs {
    suspend operator fun invoke(): Result<String>
}

class IGetObs @Inject constructor(
    private val cardRepository: CardRepository
): GetObs {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            cardRepository.getObs().getOrThrow() ?: ""
        }

}