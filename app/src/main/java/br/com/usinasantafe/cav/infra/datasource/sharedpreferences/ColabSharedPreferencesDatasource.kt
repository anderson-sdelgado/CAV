package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface ColabSharedPreferencesDatasource {
    suspend fun get(): Result<ColabCardSharedPreferencesModel>
    suspend fun clean(): EmptyResult
    suspend fun setRegColab(reg: Long): EmptyResult
    suspend fun setState(state: State): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun getRegColab(): Result<Long?>
    suspend fun getState(): Result<State?>
    suspend fun getDetail(): Result<String?>
}