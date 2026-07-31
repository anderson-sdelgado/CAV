package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDataInitialBreathalyzer {
    suspend operator fun invoke(
        flagRealized: Boolean?,
        flagResult: Boolean?,
        option: Option,
        idMain: Int
    ): Result<Unit>
}

class ISetDataInitialBreathalyzer @Inject constructor(
    private val cardRepository: CardRepository
): SetDataInitialBreathalyzer {

    override suspend fun invoke(
        flagRealized: Boolean?,
        flagResult: Boolean?,
        option: Option,
        idMain: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when (option) {
                    Option.INSERT -> setDataInitialBreathalyzer(flagRealized, flagResult)
                    Option.EDIT -> updateDataInitialBreathalyzer(flagRealized, flagResult, idMain)
                }.getOrThrow()
            }
        }

}