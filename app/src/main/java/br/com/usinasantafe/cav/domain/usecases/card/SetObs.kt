package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetObs {
    suspend operator fun invoke(text: String): EmptyResult
}

class ISetObs @Inject constructor(
    private val cardRepository: CardRepository
): SetObs {

    override suspend fun invoke(text: String): EmptyResult =
        call(getClassAndMethod()) {
            cardRepository.setObs(text).getOrThrow()
        }

}