package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface ColabSharedPreferencesDatasource {
    suspend fun setRegColab(regColab: Long): EmptyResult
    suspend fun setState(state: State): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun get(): Result<ColabSharedPreferencesModel>
    suspend fun clean(): EmptyResult
}