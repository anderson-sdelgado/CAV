package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_OPTION_DATA_LOCAL
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTableOptionDataLocal {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UpdateStatusState>
}

class IUpdateTableOptionDataLocal @Inject constructor(
    private val getToken: GetToken,
    private val dataLocalRepository: DataLocalRepository
): UpdateTableOptionDataLocal {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UpdateStatusState> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_OPTION_DATA_LOCAL)
            val token = getToken().getOrThrow()
            val entityList = dataLocalRepository.listAllOption(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_OPTION_DATA_LOCAL)
            dataLocalRepository.deleteAllOption().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_OPTION_DATA_LOCAL)
            dataLocalRepository.addAllOption(entityList).getOrThrow()

        }
    }

}