package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import javax.inject.Inject

interface SetColab {
    suspend operator fun invoke(
        regColab: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetColab @Inject constructor(
    private val cardRepository: CardRepository
): SetColab {

    override suspend fun invoke(
        regColab: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            val regColabLong = tryCatch("toLong") { regColab.toLong() }
            when {
                option == Option.INSERT -> cardRepository.setRegColab(regColabLong)
                flowNote == FlowNote.COLAB -> cardRepository.updateRegColab(regColabLong, idMain)
                else -> cardRepository.updateRegPassengerColab(regColabLong, idMain, idSecondary)
            }.getOrThrow()
        }

}
