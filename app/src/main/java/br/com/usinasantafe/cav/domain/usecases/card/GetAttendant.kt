package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetAttendant {
    suspend operator fun invoke(): Result<String>
}

class IGetAttendant @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository
): GetAttendant {

    override suspend fun invoke(): Result<String> =
        call(getClassAndMethod()) {
            val reg = cardRepository.getRegAttendant().getOrThrow()
            val name = colabRepository.getNameByReg(reg).getOrThrow()
            "$reg - $name"
        }

}