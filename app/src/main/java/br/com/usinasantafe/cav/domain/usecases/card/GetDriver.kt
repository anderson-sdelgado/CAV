package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDriver {
    suspend operator fun invoke(id: Int): Result<String>
}

class IGetDriver @Inject constructor(
): GetDriver {

    override suspend fun invoke(id: Int): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}