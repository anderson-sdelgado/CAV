package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_R_OPTION_ITEM_DATA_LOCAL
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTableROptionItemDataLocal {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UpdateStatusState>
}

class IUpdateTableROptionItemDataLocal @Inject constructor(
    private val getToken: GetToken,
    private val dataLocalRepository: DataLocalRepository
): UpdateTableROptionItemDataLocal {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UpdateStatusState> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_R_OPTION_ITEM_DATA_LOCAL)
            val token = getToken().getOrThrow()
            val entityList = dataLocalRepository.listAllROptionItem(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_R_OPTION_ITEM_DATA_LOCAL)
            dataLocalRepository.deleteAllROptionItem().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_R_OPTION_ITEM_DATA_LOCAL)
            dataLocalRepository.addAllROptionItem(entityList).getOrThrow()

        }
    }

}