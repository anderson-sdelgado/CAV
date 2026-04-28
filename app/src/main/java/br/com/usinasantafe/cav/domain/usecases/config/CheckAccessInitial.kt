package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface CheckAccessInitial {
    suspend operator fun invoke(): Result<Boolean>
}

class ICheckAccessInitial @Inject constructor(
    private val configRepository: ConfigRepository
): CheckAccessInitial {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            val hasConfig = configRepository.has().getOrThrow()
            if (!hasConfig) return@call false
            configRepository.getFlagUpdate().getOrThrow()
        }

}