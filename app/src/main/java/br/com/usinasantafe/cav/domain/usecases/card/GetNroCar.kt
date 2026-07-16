package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetNroCar {
    suspend operator fun invoke(): Result<Long?>
}

class IGetNroCar @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): GetNroCar {

    override suspend fun invoke(): Result<Long?> =
        call(getClassAndMethod()) {
            val id = cardRepository.getIdCar().getOrThrow() ?: return@call null
            equipRepository.getById(id).getOrThrow().nro
        }

}