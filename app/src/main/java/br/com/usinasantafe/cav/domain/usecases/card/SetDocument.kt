package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDocument {
    suspend operator fun invoke(
        cpf: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetDocument @Inject constructor(
): SetDocument {

    override suspend fun invoke(
        cpf: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}