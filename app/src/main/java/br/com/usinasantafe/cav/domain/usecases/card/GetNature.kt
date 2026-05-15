package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetNature {
    suspend operator fun invoke(): Result<String>
}

class IGetNature @Inject constructor(
    private val cardRepository: CardRepository,
    private val natureRepository: NatureRepository
): GetNature {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            val idList = cardRepository.listIdNature().getOrThrow()
            val entityList = natureRepository.listByIdList(idList).getOrThrow()
            if(entityList.isEmpty()) return@call "-"
            entityList.joinToString(separator = " - ") { it.description }
        }

}