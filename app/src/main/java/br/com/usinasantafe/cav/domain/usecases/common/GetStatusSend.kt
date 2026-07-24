package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.callFlow
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetStatusSend {
    suspend operator fun invoke(): Flow<StatusSend>
}

class IGetStatusSend @Inject constructor(
    private val configRepository: ConfigRepository
): GetStatusSend {

    override suspend fun invoke(): Flow<StatusSend> =
        callFlow(getClassAndMethod()) {
            configRepository.getStatusSend()
        }

}