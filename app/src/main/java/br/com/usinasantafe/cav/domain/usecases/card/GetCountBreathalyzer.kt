package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetCountBreathalyzer {
    suspend operator fun invoke(
        option: Option,
        idMain: Int
    ): Result<Double?>
}

class IGetCountBreathalyzer @Inject constructor(
    private val cardRepository: CardRepository
): GetCountBreathalyzer {

    override suspend fun invoke(
        option: Option,
        idMain: Int
    ): Result<Double?> =
        call(getClassAndMethod()) {
            when(option){
                Option.INSERT -> cardRepository.getCountBreathalyzer()
                Option.EDIT -> cardRepository.getCountBreathalyzer(idMain)
            }.getOrThrow()
        }

}