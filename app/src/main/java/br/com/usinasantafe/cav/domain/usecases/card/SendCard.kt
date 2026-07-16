package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SendCard {
    suspend operator fun invoke(): Result<Boolean>
}

class ISendCard @Inject constructor(
    private val getToken: GetToken,
    private val cardRepository: CardRepository,
): SendCard {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            val token = getToken().getOrThrow()
            cardRepository.send(token).getOrThrow()
            cardRepository.hasSend().getOrThrow()
        }

}