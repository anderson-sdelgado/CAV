package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface HasNature {
    suspend operator fun invoke(): Result<Boolean>
}

class IHasNature @Inject constructor(
    private val cardRepository: CardRepository
): HasNature {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            !cardRepository.listIdNature().getOrThrow().isEmpty()
        }

}