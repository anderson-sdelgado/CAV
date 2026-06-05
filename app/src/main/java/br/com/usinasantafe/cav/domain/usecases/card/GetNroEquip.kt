package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetNroEquip {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetNroEquip @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): GetNroEquip {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            val idEquip = when(flowNote) {
                FlowNote.EQUIP -> cardRepository.getIdEquip(idMain).getOrThrow()
                else -> cardRepository.getIdEquipSecondary(idMain, idSecondary).getOrThrow()
            }
            equipRepository.getNroById(idEquip).getOrThrow().toString()
        }

}
