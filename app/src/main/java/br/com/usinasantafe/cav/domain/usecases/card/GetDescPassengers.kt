package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescPassengers {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<String>
}

class IGetDescPassengers @Inject constructor(
): GetDescPassengers {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
