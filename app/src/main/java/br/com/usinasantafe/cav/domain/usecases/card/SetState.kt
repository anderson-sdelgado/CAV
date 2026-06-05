package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetState {
    suspend operator fun invoke(
        idSelection: Int,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit>
}

class ISetState @Inject constructor(
): SetState {

    override suspend fun invoke(
        idSelection: Int,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
