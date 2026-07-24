package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.domain.usecases.background.StartWorkManager
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface StartFlow {
    suspend operator fun invoke(): Result<Boolean>
}

class IStartFlow @Inject constructor(
    private val cardRepository: CardRepository,
    private val startWorkManager: StartWorkManager
): StartFlow {

    override suspend fun invoke(): Result<Boolean> =
        call(getClassAndMethod()) {
            startWorkManager()
            cardRepository.delete().getOrThrow()
            cardRepository.has().getOrThrow()
        }

}