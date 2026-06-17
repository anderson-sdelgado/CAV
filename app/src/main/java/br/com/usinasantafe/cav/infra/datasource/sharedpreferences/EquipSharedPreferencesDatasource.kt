package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipSharedPreferencesDatasource {
    suspend fun setIdEquip(idEquip: Int): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun get(): Result<EquipSharedPreferencesModel>
    suspend fun clean(): EmptyResult
}