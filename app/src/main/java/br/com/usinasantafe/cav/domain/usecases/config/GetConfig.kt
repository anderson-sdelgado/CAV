package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.presenter.model.ConfigModel
import br.com.usinasantafe.cav.presenter.model.toConfigModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.text.get

interface GetConfig {
    suspend operator fun invoke(): Result<ConfigModel?>
}

class IGetConfig @Inject constructor(
    private val configRepository: ConfigRepository
): GetConfig {

    override suspend fun invoke(): Result<ConfigModel?> =
        call(getClassAndMethod()) {
            val hasConfig = configRepository.has().getOrThrow()
            if (!hasConfig) return@call null
            val config = configRepository.get().getOrThrow()
            config.toConfigModel()
        }

}