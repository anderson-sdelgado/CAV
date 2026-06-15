package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteWitness {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteWitness @Inject constructor(
    private val cardRepository: CardRepository
): DeleteWitness {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            cardRepository.deleteWitness(id).getOrThrow()
        }

}
