package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetBrand {
    suspend operator fun invoke(
        text: String,
        option: Option,
        idMain: Int
    ): Result<Unit>
}

class ISetBrand @Inject constructor(
    private val cardRepository: CardRepository
): SetBrand {

    override suspend fun invoke(
        text: String,
        option: Option,
        idMain: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository){
                when(option) {
                    Option.INSERT -> setBrand(text)
                    Option.EDIT -> updateBrand(text, idMain)
                }.getOrThrow()
            }
        }

}
