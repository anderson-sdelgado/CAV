package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteInvolved {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteInvolved @Inject constructor(
    private val cardRepository: CardRepository
): DeleteInvolved {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            cardRepository.deleteInvolved(id).getOrThrow()
        }

}
