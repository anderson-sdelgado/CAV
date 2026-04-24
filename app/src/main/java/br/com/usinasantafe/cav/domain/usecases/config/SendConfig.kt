package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface SendConfig {
    suspend operator fun invoke(
        number: String,
        password: String,
        version: String,
    ): Result<Config>
}

class ISendConfig @Inject constructor(
    private val configRepository: ConfigRepository
): SendConfig {

    override suspend fun invoke(
        number: String,
        password: String,
        version: String,
    ): Result<Config> =
        call(getClassAndMethod()) {

            val numberLong = tryCatch(::toLong.name) {
                number.toLong()
            }

            val entity = Config(
                number = numberLong,
                password = password,
                version = version
            )
            configRepository.send(entity).getOrThrow()
        }

}