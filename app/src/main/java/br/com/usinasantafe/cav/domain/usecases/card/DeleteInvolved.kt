package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteInvolved {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteInvolved @Inject constructor(
): DeleteInvolved {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
