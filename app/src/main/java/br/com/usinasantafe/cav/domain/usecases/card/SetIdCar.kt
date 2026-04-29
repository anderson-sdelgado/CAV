package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface SetIdCar {
    suspend operator fun invoke(nroEquip: String): Result<Unit>
}

class ISetIdCar @Inject constructor(
    private val equipRepository: EquipRepository,
    private val cardRepository: CardRepository
): SetIdCar {

    override suspend fun invoke(nroEquip: String): Result<Unit> =
        call(getClassAndMethod()) {
            val nroEquipLong = tryCatch(::toLong.name) {
                nroEquip.toLong()
            }
            val idEquip = equipRepository.getIdByNro(nroEquipLong).getOrThrow()
            cardRepository.setIdCar(idEquip).getOrThrow()
        }

}