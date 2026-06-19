package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetNroEquip {
    suspend operator fun invoke(
        option: Option,
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
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            val idEquip = when {
                option == Option.INSERT -> cardRepository.getIdEquip()
                flowNote == FlowNote.EQUIP -> cardRepository.getIdEquip(idMain)
                else -> cardRepository.getIdEquipSecondary(idMain, idSecondary)
            }.getOrThrow()
            if (idEquip == null) return@call ""
            equipRepository.getNroById(idEquip).getOrThrow().toString()
        }

}
