package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteEquipSec {
    suspend operator fun invoke(
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class IDeleteEquipSec @Inject constructor(
    private val cardRepository: CardRepository
): DeleteEquipSec {

    override suspend fun invoke(
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            cardRepository.deleteEquipSecondary(idMain, idSecondary).getOrThrow()
        }

}
