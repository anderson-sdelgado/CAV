package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetEquip {
    suspend operator fun invoke(
        nroEquip: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetEquip @Inject constructor(
): SetEquip {

    override suspend fun invoke(
        nroEquip: String,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}