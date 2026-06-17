package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardSharedPreferencesDatasource {
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
    suspend fun setLocal(model: LocalSharedPreferencesModel): EmptyResult
    suspend fun listIdNature(): Result<List<Int>>
    suspend fun setIdNatureList(idList: List<Int>): EmptyResult
    suspend fun getRegAttendant(): Result<Long>
    suspend fun getIdCar(): Result<Int>
    suspend fun listIdTypeAccident(): Result<List<Int>>
    suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult
    suspend fun clean(): EmptyResult
    suspend fun has(): Result<Boolean>
    suspend fun getLocal(): Result<LocalSharedPreferencesModel>
    suspend fun listIdDataLocal(): Result<List<Int>>
    suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult
    suspend fun listIdSupportTeams(): Result<List<Int>>
    suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult
    suspend fun setVehicleOwn(entity: VehicleOwnSharedPreferencesModel): Result<Int>
    suspend fun setEquipSec(equipSharedPreferencesModel: EquipSharedPreferencesModel): EmptyResult
}