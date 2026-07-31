package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_INVOLVED
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IInvolvedSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): InvolvedSharedPreferencesDatasource {

    suspend fun updateModel(block: PeopleExternalSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: PeopleExternalSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: PeopleExternalSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_INVOLVED,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<PeopleExternalSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_INVOLVED,
                null
            )
            if (data.isNullOrEmpty()) return@result PeopleExternalSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                PeopleExternalSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun clean(): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_INVOLVED,
                    null
                )
            }
        }

    override suspend fun setDocument(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { document = text }
        }

    override suspend fun setName(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { name = text }
        }

    override suspend fun setPhone(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { phone = text }
        }

    override suspend fun setState(state: State): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { this.state = state }
        }

    override suspend fun setDetail(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { detail = text }
        }

    override suspend fun getDocument(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { document }
        }

    override suspend fun getPhone(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { phone }
        }

    override suspend fun getName(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { name }
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