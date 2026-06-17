package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IColabSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ColabSharedPreferencesDatasource {

    override suspend fun setRegColab(regColab: Long): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setState(state: State): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetail(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Result<ColabSharedPreferencesModel> {
        TODO("Not yet implemented")
    }

    override suspend fun clean(): EmptyResult {
        TODO("Not yet implemented")
    }

}