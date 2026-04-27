package br.com.usinasantafe.cav.domain.usecases.update

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.usecases.common.GetToken
import br.com.usinasantafe.cav.lib.LevelUpdate
import br.com.usinasantafe.cav.lib.TB_EQUIP
import br.com.usinasantafe.cav.utils.UpdateStatusState
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.emitProgress
import br.com.usinasantafe.cav.utils.flowCall
import br.com.usinasantafe.cav.utils.getClassAndMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTableEquip {
    suspend operator fun invoke(
        sizeAll: Float,
        count: Float = 1f
    ): Flow<UpdateStatusState>
}

class IUpdateTableEquip @Inject constructor(
    private val getToken: GetToken,
    private val equipRepository: EquipRepository
): UpdateTableEquip {

    override suspend fun invoke(
        sizeAll: Float,
        count: Float
    ): Flow<UpdateStatusState> = flow {
        flowCall(getClassAndMethod()) {

            emitProgress(count, sizeAll, LevelUpdate.RECOVERY, TB_EQUIP)
            val token = getToken().getOrThrow()
            val entityList = equipRepository.listAll(token).getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.CLEAN, TB_EQUIP)
            equipRepository.deleteAll().getOrThrow()

            emitProgress(count, sizeAll, LevelUpdate.SAVE, TB_EQUIP)
            equipRepository.addAll(entityList).getOrThrow()

        }
    }

}