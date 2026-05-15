package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetTypeAccident {
    suspend operator fun invoke(): Result<String>
}

class IGetTypeAccident @Inject constructor(
    private val cardRepository: CardRepository,
    private val typeAccidentRepository: TypeAccidentRepository
): GetTypeAccident {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            val idList = cardRepository.listIdTypeAccident().getOrThrow()
            val entityList = typeAccidentRepository.listByIdList(idList).getOrThrow()
            if(entityList.isEmpty()) return@call "-"
            entityList.joinToString(separator = " - ") { it.description }
        }

}