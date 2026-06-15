package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescColab {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int = 0,
    ): Result<String>
}

class IGetDescColab @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository
): GetDescColab {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String> =
        call(getClassAndMethod()) {
            val reg = when (flowNote) {
                FlowNote.COLAB -> cardRepository.getRegColab(idMain)
                else -> cardRepository.getRegPassengerColab(idMain, idSecondary)
            }.getOrThrow()
            val name = colabRepository.getNameByReg(reg).getOrThrow()
            "$reg - $name"
        }

}
