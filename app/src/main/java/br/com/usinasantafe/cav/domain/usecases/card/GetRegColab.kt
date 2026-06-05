package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetRegColab {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetRegColab @Inject constructor(
    private val cardRepository: CardRepository
): GetRegColab {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            when (flowNote) {
                FlowNote.COLAB -> cardRepository.getRegColab(idMain)
                else -> cardRepository.getRegPassengerColab(idMain, idSecondary)
            }.getOrThrow().toString()
        }

}
