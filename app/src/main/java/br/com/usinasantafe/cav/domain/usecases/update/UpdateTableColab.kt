package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_COLAB
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.collections.addAll

interface UpdateTableColab {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UpdateStatusState>
}

class IUpdateTableColab @Inject constructor(
    private val getToken: GetToken,
    private val colabRepository: ColabRepository
): UpdateTableColab {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UpdateStatusState> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_COLAB)
            val token = getToken().getOrThrow()
            val entityList = colabRepository.listAll(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_COLAB)
            colabRepository.deleteAll().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_COLAB)
            colabRepository.addAll(entityList).getOrThrow()

        }
    }

}