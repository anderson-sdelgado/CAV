package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeleteWitness {
    suspend operator fun invoke(id: Int): Result<Unit>
}

class IDeleteWitness @Inject constructor(
): DeleteWitness {

    override suspend fun invoke(id: Int): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}