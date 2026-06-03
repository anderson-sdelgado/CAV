package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface SetEquip {
    suspend operator fun invoke(
        nroEquip: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit>
}

class ISetEquip @Inject constructor(
    private val equipRepository: EquipRepository,
    private val cardRepository: CardRepository,
): SetEquip {

    override suspend fun invoke(
        nroEquip: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Unit> =
        call(getClassAndMethod()) {
            val nroEquipLong = tryCatch(::toLong.name) {
                nroEquip.toLong()
            }
            val idEquip = equipRepository.getIdByNro(nroEquipLong).getOrThrow()
            if(option == Option.INSERT){
                cardRepository.setIdEquip(idEquip)
                return@call
            }
        }

}