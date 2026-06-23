package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescOption {
    suspend operator fun invoke(id: Int): Result<String>
}

class IGetDescOption @Inject constructor(
    private val dataLocalRepository: DataLocalRepository
): GetDescOption {

    override suspend fun invoke(id: Int): Result<String> =
        call(getClassAndMethod()) {
            dataLocalRepository.getDescOptionById(id).getOrThrow()
        }

}