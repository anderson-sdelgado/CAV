package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetRealizedBreathalyzer {
    suspend operator fun invoke(
        option: Option,
        idMain: Int
    ): Result<Boolean?>
}

class IGetRealizedBreathalyzer @Inject constructor(
    private val cardRepository: CardRepository
): GetRealizedBreathalyzer {

    override suspend fun invoke(
        option: Option,
        idMain: Int
    ): Result<Boolean?> =
        call(getClassAndMethod()) {
            when(option){
                Option.INSERT -> cardRepository.getRealizedBreathalyzer()
                Option.EDIT -> cardRepository.getRealizedBreathalyzer(idMain)
            }.getOrThrow()
        }

}