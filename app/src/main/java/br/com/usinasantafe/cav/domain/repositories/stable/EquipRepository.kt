package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipRepository {
    suspend fun addAll(list: List<Equip>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<Equip>>
}