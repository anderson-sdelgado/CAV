package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.EmptyResult

interface ConfigSharedPreferencesDatasource {
    suspend fun get(): Result<ConfigSharedPreferencesModel>
    suspend fun has(): Result<Boolean>
    suspend fun getPassword(): Result<String>
    suspend fun save(model: ConfigSharedPreferencesModel): EmptyResult
    suspend fun setFlagUpdate(): EmptyResult
    suspend fun getFlagUpdate(): Result<Boolean>
    suspend fun getStatusSend(): Result<StatusSend>
}