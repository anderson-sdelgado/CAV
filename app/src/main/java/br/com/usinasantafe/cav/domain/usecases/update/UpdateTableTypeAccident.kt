package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_SUPPORT_TEAMS
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTableTypeAccident {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UpdateStatusState>
}

class IUpdateTableTypeAccident @Inject constructor(
    private val getToken: GetToken,
    private val typeAccidentRepository: TypeAccidentRepository
): UpdateTableTypeAccident {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UpdateStatusState> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_SUPPORT_TEAMS)
            val token = getToken().getOrThrow()
            val entityList = typeAccidentRepository.listAll(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_SUPPORT_TEAMS)
            typeAccidentRepository.deleteAll().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_SUPPORT_TEAMS)
            typeAccidentRepository.addAll(entityList).getOrThrow()

        }
    }

}