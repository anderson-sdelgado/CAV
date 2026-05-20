package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRepository {
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
    suspend fun setLocal(entity: Local): EmptyResult
    suspend fun listIdNature(): Result<List<Int>>
    suspend fun setIdNatureList(idList: List<Int>): EmptyResult
    suspend fun getRegAttendant(): Result<Long>
    suspend fun getIdCar(): Result<Int>
    suspend fun listIdTypeAccident(): Result<List<Int>>
    suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult
    suspend fun clean(): EmptyResult
    suspend fun getLocal(): Result<Local>
    suspend fun listIdDataLocal(): Result<List<Int>>
    suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult
    suspend fun listIdSupportTeams(): Result<List<Int>>
    suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult
}