package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetResultBreathalyzer {
    suspend operator fun invoke(
        option: Option,
        idMain: Int
    ): Result<Boolean?>
}

class IGetResultBreathalyzer @Inject constructor(
    private val cardRepository: CardRepository
): GetResultBreathalyzer {

    override suspend fun invoke(
        option: Option,
        idMain: Int
    ): Result<Boolean?> =
        call(getClassAndMethod()) {
            when(option){
                Option.INSERT -> cardRepository.getResultBreathalyzer()
                Option.EDIT -> cardRepository.getResultBreathalyzer(idMain)
            }.getOrThrow()
        }

}