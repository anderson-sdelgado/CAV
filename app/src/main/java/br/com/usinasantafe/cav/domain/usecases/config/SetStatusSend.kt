package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetStatusSend {
    suspend operator fun invoke(statusSend: StatusSend): EmptyResult
}

class ISetStatusSend @Inject constructor(
    private val configRepository: ConfigRepository
): SetStatusSend {

    override suspend fun invoke(statusSend: StatusSend): EmptyResult =
        call(getClassAndMethod()) {
            configRepository.setStatusSend(statusSend).getOrThrow()
        }

}