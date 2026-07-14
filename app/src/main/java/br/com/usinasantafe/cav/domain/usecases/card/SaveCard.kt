package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SaveCard {
    suspend operator fun invoke(): Result<Boolean>
}

class ISaveCard @Inject constructor(
    private val cardRepository: CardRepository
): SaveCard {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            if(!cardRepository.hasLocal().getOrThrow()) return@call false
            if(cardRepository.listIdNature().getOrThrow().isEmpty()) return@call false
            if(cardRepository.listIdTypeAccident().getOrThrow().isEmpty()) return@call false
            if(cardRepository.listIdDataLocal().getOrThrow().isEmpty()) return@call false
            if(cardRepository.listVehicleOwn().getOrThrow().isEmpty()) return@call false
            cardRepository.save().getOrThrow()
            true
        }

}