package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetName {
    suspend operator fun invoke(
        name: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetName @Inject constructor(
): SetName {

    override suspend fun invoke(
        name: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}