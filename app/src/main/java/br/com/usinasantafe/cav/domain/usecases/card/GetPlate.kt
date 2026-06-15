package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetPlate {
    suspend operator fun invoke(idMain: Int): Result<String>
}

class IGetPlate @Inject constructor(
    private val cardRepository: CardRepository
): GetPlate {

    override suspend fun invoke(idMain: Int): Result<String> =
        call(getClassAndMethod()) {
            cardRepository.getPlate(idMain).getOrThrow() ?: ""
        }

}
