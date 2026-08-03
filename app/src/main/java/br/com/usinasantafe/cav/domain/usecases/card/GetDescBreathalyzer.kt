package br.com.usinasantafe.cav.domain.usecases.card

import android.content.Context
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.doubleToString
import br.com.usinasantafe.cav.utils.getClassAndMethod
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface GetDescBreathalyzer {
    suspend operator fun invoke(idMain: Int): Result<String>
}

class IGetDescBreathalyzer @Inject constructor(
    @param:ApplicationContext
    private val context: Context,
    private val cardRepository: CardRepository
): GetDescBreathalyzer {

    override suspend fun invoke(idMain: Int): Result<String> =
        call(getClassAndMethod()) {
            val breathalyzer = cardRepository.getBreathalyzer(idMain).getOrThrow()
            val (result, realized, count) = breathalyzer
            
            val realizedStr = when (realized) {
                true -> context.getString(R.string.text_realized_yes)
                false -> context.getString(R.string.text_realized_no)
                null -> ""
            }
            
            val resultStr = when (result) {
                true -> " - ${context.getString(R.string.text_pattern_positive)}"
                false -> " - ${context.getString(R.string.text_pattern_negative)}"
                null -> ""
            }
            
            val countStr = if (count == null) "" else " - ${doubleToString(count, 2)}"
            
            "$realizedStr$resultStr$countStr"
        }

}
