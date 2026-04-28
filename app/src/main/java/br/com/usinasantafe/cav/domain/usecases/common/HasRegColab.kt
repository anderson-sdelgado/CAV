package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedBytes.toInt
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface HasRegColab {
    suspend operator fun invoke(regColab: String): Result<Boolean>
}

class IHasRegColab @Inject constructor(
    private val colabRepository: ColabRepository
): HasRegColab {

    override suspend fun invoke(regColab: String): Result<Boolean> =
        call(getClassAndMethod()) {
            val regColabLong = tryCatch(::toLong.name) {
                regColab.toLong()
            }
            colabRepository.hasReg(regColabLong).getOrThrow()
        }

}