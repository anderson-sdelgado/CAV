package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDocument {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetDocument @Inject constructor(
): GetDocument {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}