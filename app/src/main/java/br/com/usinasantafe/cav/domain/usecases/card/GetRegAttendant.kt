package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetRegAttendant {
    suspend operator fun invoke(): Result<Long?>
}

class IGetRegAttendant @Inject constructor(
    private val cardRepository: CardRepository
): GetRegAttendant {

    override suspend fun invoke(): Result<Long?> =
        call(getClassAndMethod()) {
            cardRepository.getRegAttendant().getOrThrow()
        }

}