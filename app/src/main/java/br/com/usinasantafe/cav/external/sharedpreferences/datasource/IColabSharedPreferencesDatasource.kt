package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IColabSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ColabSharedPreferencesDatasource {

    suspend fun updateModel(block: ColabCardSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: ColabCardSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: ColabCardSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<ColabCardSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR,
                null
            )
            if (data.isNullOrEmpty()) return@result ColabCardSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                ColabCardSharedPreferencesModel::class.java
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

    override suspend fun getResultBreathalyzer(): Result<Boolean?> =
        result(getClassAndMethod()) {
            readModel { flagResultBreathalyzer }
        }

    override suspend fun getRealizedBreathalyzer(): Result<Boolean?> =
        result(getClassAndMethod()) {
            readModel { flagRealizedBreathalyzer }
        }

    override suspend fun getCountBreathalyzer(): Result<Double?> =
        result(getClassAndMethod()) {
            readModel { countBreathalyzer }
        }

    override suspend fun setDataInitialBreathalyzer(flagRealized: Boolean?, flagResult: Boolean?): EmptyResult =
        result(getClassAndMethod()) {
            updateModel {
                this.flagResultBreathalyzer = flagResult
                this.flagRealizedBreathalyzer = flagRealized
                if((flagRealized != true) || (flagResult != true)) this.countBreathalyzer = null
            }
        }

    override suspend fun setCountBreathalyzer(count: Double?): EmptyResult =
        result(getClassAndMethod()) {
            updateModel {
                this.countBreathalyzer = count
            }
        }

}