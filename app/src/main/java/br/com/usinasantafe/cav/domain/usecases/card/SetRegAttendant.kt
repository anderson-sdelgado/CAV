package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface SetRegAttendant {
    suspend operator fun invoke(regColab: String): EmptyResult
}

class ISetRegAttendant @Inject constructor(
    private val cardRepository: CardRepository
): SetRegAttendant {

    override suspend fun invoke(regColab: String): EmptyResult =
        call(getClassAndMethod()) {
            val regColabLong = tryCatch(::toLong.name) {
                regColab.toLong()
            }
            cardRepository.setRegAttendant(regColabLong).getOrThrow()
        }

}