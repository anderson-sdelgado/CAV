package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetFinishUpdateAllTable {
    suspend operator fun invoke(): EmptyResult
}

class ISetFinishUpdateAllTable @Inject constructor(
    private val configRepository: ConfigRepository
): SetFinishUpdateAllTable {

    override suspend fun invoke(): EmptyResult =
        call(getClassAndMethod()) {
            configRepository.setFlagUpdate().getOrThrow()
        }

}