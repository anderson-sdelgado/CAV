package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface InvolvedSharedPreferencesDatasource {
    suspend fun get(): Result<InvolvedSharedPreferencesModel>
    suspend fun clean(): EmptyResult
    suspend fun setDetail(text: String): EmptyResult

    suspend fun setDocument(text: String): EmptyResult
    suspend fun setState(state: State): EmptyResult
    suspend fun setName(text: String): EmptyResult
}