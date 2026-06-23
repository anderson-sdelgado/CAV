package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescEquipSec {
    suspend operator fun invoke(id: Int): Result<String>
}

class IGetDescEquipSec @Inject constructor(
    private val cardRepository: CardRepository,
    private val equipRepository: EquipRepository
): GetDescEquipSec {

    override suspend fun invoke(id: Int): Result<String> =
        call(getClassAndMethod()) {
            val equipList = cardRepository.listEquipSecondary(id).getOrThrow()
            val idEquipList = equipList.map { it.idEquip!! }
            val entityList = equipRepository.listByIdList(idEquipList).getOrThrow()
            val descList = entityList.map {  "${it.nro} - ${it.description}" }
            descList.joinToString(separator = "\n")
        }

}
