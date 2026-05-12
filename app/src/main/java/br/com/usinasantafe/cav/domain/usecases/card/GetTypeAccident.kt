package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetTypeAccident {
    suspend operator fun invoke(): Result<String>
}

class IGetTypeAccident @Inject constructor(
): GetTypeAccident {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}