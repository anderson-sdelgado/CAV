package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IColabSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ColabSharedPreferencesDatasource {

    suspend fun updateModel(block: ColabSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: ColabSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: ColabSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<ColabSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR,
                null
            )
            if (data.isNullOrEmpty()) return@result ColabSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                ColabSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun clean(): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR,
                    null
                )
            }
        }

    override suspend fun setRegColab(reg: Long): EmptyResult =
        result(getClassAndMethod()) {
            clean()
            updateModel {
                this.reg = reg
            }
        }

    override suspend fun setState(state: State): EmptyResult =
        result(getClassAndMethod()) {
            updateModel {
                this.state = state
            }
        }

    override suspend fun setDetail(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel {
                this.detail = text
            }
        }

    override suspend fun getRegColab(): Result<Long?> =
        result(getClassAndMethod()) {
            readModel { reg }
        }

    override suspend fun getState(): Result<State?> =
        result(getClassAndMethod()) {
            readModel { state }
        }

    override suspend fun getDetail(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { detail }
        }

}