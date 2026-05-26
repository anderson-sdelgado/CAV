package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_DATA_LOCAL
import br.com.usinasantafe.cav.utils.UiStatusStateUpdate
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTableDataLocal {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UiStatusStateUpdate>
}

class IUpdateTableDataLocal @Inject constructor(
    private val getToken: GetToken,
    private val dataLocalRepository: DataLocalRepository
): UpdateTableDataLocal {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UiStatusStateUpdate> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_DATA_LOCAL)
            val token = getToken().getOrThrow()
            val entityList = dataLocalRepository.listAllDataLocal(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_DATA_LOCAL)
            dataLocalRepository.deleteAllDataLocal().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_DATA_LOCAL)
            dataLocalRepository.addAllDataLocal(entityList).getOrThrow()

        }
    }

}