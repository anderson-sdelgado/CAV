package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface HasSendCard {
    suspend operator fun invoke(): Result<Boolean>
}

class IHasSendCard @Inject constructor(
    private val cardRepository: CardRepository
): HasSendCard {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            cardRepository.hasSend().getOrThrow()
        }

}