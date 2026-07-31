package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface InvolvedSharedPreferencesDatasource {
    suspend fun get(): Result<PeopleExternalSharedPreferencesModel>
    suspend fun clean(): EmptyResult
    suspend fun setDocument(text: String): EmptyResult
    suspend fun setName(text: String): EmptyResult
    suspend fun setPhone(text: String): EmptyResult
    suspend fun setState(state: State): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun getDocument(): Result<String?>
    suspend fun getName(): Result<String?>
    suspend fun getPhone(): Result<String?>
    suspend fun getState(): Result<State?>
    suspend fun getDetail(): Result<String?>
}