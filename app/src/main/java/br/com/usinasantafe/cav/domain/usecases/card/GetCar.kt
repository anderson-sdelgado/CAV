package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetCar {
    suspend operator fun invoke(): Result<String>
}

class IGetCar @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): GetCar {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            val id = cardRepository.getIdCar().getOrThrow()
            val entity = equipRepository.getById(id).getOrThrow()
            "${entity.nro} - ${entity.description}"
        }

}