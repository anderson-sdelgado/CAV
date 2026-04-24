package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SaveConfig {
    suspend operator fun invoke(
        number: String,
        password: String,
        version: String,
        idServ: Int
    ): Result<Unit>
}

class ISaveConfig @Inject constructor(
): SaveConfig {

    override suspend fun invoke(
        number: String,
        password: String,
        version: String,
        idServ: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}