package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ConfigSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_CONFIG
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IConfigSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ConfigSharedPreferencesDatasource {

    suspend fun save(model: ConfigSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_CONFIG,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<ConfigSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val config = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CONFIG,
                null
            )
            if(config.isNullOrEmpty()) return@result ConfigSharedPreferencesModel()
            val model = Gson().fromJson(
                config,
                ConfigSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun has(): Result<Boolean> =
        result(getClassAndMethod()) {
            val result = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CONFIG,
                null
            )
            result != null
        }

    override suspend fun getPassword(): Result<String> =
        result(getClassAndMethod()) {
            get().getOrThrow()::password.required()
        }

}