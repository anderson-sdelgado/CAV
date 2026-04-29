package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import com.google.common.primitives.UnsignedInts.toLong
import javax.inject.Inject

interface HasNroEquip {
    suspend operator fun invoke(nroEquip: String): Result<Boolean>
}

class IHasNroEquip @Inject constructor(
    private val equipRepository: EquipRepository
): HasNroEquip {

    override suspend fun invoke(nroEquip: String): Result<Boolean> =
        call(getClassAndMethod()) {
            val nroEquipLong = tryCatch(::toLong.name) {
                nroEquip.toLong()
            }
            equipRepository.hasNro(nroEquipLong).getOrThrow()
        }

}