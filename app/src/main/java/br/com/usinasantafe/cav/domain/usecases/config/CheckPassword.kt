package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface CheckPassword {
    suspend operator fun invoke(password: String): Result<Boolean>
}

class ICheckPassword @Inject constructor(
    private val configRepository: ConfigRepository
): CheckPassword {

    override suspend fun invoke(password: String): Result<Boolean> =
        call(getClassAndMethod()) {
            val hasConfig = configRepository.has().getOrThrow()
            if (!hasConfig) return@call true
            val passwordConfig = configRepository.getPassword().getOrThrow()
            passwordConfig == password
        }

}