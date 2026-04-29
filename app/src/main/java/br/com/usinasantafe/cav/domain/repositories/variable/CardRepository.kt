package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRepository {
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
}