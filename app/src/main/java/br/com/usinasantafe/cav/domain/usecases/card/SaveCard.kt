package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SaveCard {
    suspend operator fun invoke(): EmptyResult
}

class ISaveCard @Inject constructor(
    private val cardRepository: CardRepository
): SaveCard {

    override suspend fun invoke(): EmptyResult =
        call(getClassAndMethod()) {
            cardRepository.save().getOrThrow()
            cardRepository.clean().getOrThrow()
        }

}