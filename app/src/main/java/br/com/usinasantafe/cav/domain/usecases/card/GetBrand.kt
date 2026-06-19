package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetBrand {
    suspend operator fun invoke(
        option: Option,
        idMain: Int
    ): Result<String>
}

class IGetBrand @Inject constructor(
    private val cardRepository: CardRepository
): GetBrand {

    override suspend fun invoke(
        option: Option,
        idMain: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            when(option){
                Option.INSERT -> cardRepository.getBrand()
                Option.EDIT -> cardRepository.getBrand(idMain)
            }.getOrThrow() ?: ""
        }

}
