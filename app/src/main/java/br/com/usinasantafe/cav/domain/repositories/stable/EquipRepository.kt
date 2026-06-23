package br.com.usinasantafe.cav.domain.repositories.stable

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipRepository {
    suspend fun addAll(list: List<Equip>): EmptyResult
    suspend fun deleteAll(): EmptyResult
    suspend fun listAll(token: String): Result<List<Equip>>
    suspend fun hasNro(nro: Long): Result<Boolean>
    suspend fun getIdByNro(nro: Long): Result<Int>
    suspend fun getById(id: Int): Result<Equip>
    suspend fun getNroById(id: Int): Result<Long>
    suspend fun listByIdList(idList: List<Int>): Result<List<Equip>>
}