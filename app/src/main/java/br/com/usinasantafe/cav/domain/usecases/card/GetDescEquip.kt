package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescEquip {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int = 0,
    ): Result<String>
}

class IGetDescEquip @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): GetDescEquip {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int,
    ): Result<String> =
        call(getClassAndMethod()) {
            val id = when(flowNote) {
                FlowNote.EQUIP -> cardRepository.getIdEquip(idMain)
                else -> cardRepository.getIdEquipSecondary(idMain, idSecondary)
            }.getOrThrow()
            val entity = equipRepository.getById(id).getOrThrow()
            "${entity.nro} - ${entity.description}"
        }

}
