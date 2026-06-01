package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDetail {
    suspend operator fun invoke(
        text: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetDetail @Inject constructor(
): SetDetail {

    override suspend fun invoke(
        text: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}