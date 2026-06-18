package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
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

    suspend fun updateModel(block: InvolvedSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: InvolvedSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: InvolvedSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_INVOLVED,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<InvolvedSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_INVOLVED,
                null
            )
            if (data.isNullOrEmpty()) return@result InvolvedSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                InvolvedSharedPreferencesModel::class.java
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

    override suspend fun setDocument(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setState(state: State): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setName(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetail(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

}