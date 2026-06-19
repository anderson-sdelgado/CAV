package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipSharedPreferencesDatasource {
    suspend fun get(): Result<EquipSharedPreferencesModel>
    suspend fun clean(): EmptyResult
    suspend fun setIdEquip(idEquip: Int): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun getIdEquip(): Result<Int?>
    suspend fun getDetail(): Result<String?>
}