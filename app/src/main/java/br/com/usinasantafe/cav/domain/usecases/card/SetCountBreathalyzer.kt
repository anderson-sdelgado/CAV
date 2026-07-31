package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.stringToDouble
import br.com.usinasantafe.cav.utils.tryCatch
import javax.inject.Inject

interface SetCountBreathalyzer {
    suspend operator fun invoke(
        text: String,
        option: Option,
        idMain: Int
    ): Result<Unit>
}

class ISetCountBreathalyzer @Inject constructor(
    private val cardRepository: CardRepository
): SetCountBreathalyzer {

    override suspend fun invoke(
        text: String,
        option: Option,
        idMain: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                val value = tryCatch(::stringToDouble.name) { stringToDouble(text) }
                when (option) {
                    Option.INSERT -> setCountBreathalyzer(value)
                    Option.EDIT -> updateCountBreathalyzer(value, idMain)
                }.getOrThrow()
            }
        }

}