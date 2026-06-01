package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetIdState {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Int>
}

class IGetIdState @Inject constructor(
): GetIdState {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<Int> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}