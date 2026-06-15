package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescDriver {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
    ): Result<String>
}

class IGetDescDriver @Inject constructor(
    private val cardRepository: CardRepository
): GetDescDriver {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            val document = cardRepository.getDocumentDriver(idMain).getOrThrow() ?: "-"
            val name = cardRepository.getNameDriver(idMain).getOrThrow()
            "$document - $name"
        }

}
