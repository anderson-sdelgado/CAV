package br.com.usinasantafe.cav.domain.usecases.note

import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetRegAttendant {
    suspend operator fun invoke(regColab: String): Result<Unit>
}

class ISetRegAttendant @Inject constructor(
): SetRegAttendant {

    override suspend fun invoke(regColab: String): Result<Unit> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}