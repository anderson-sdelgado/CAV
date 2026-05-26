package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeletePassenger {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeletePassenger @Inject constructor(
): DeletePassenger {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}