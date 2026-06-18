package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_COLLABORATOR
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_EQUIP
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IEquipSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): EquipSharedPreferencesDatasource {

    suspend fun updateModel(block: EquipSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: EquipSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: EquipSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_EQUIP,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<EquipSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_EQUIP,
                null
            )
            if (data.isNullOrEmpty()) return@result EquipSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                EquipSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun clean(): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_EQUIP,
                    null
                )
            }
        }

    override suspend fun setIdEquip(idEquip: Int): EmptyResult =
        result(getClassAndMethod()) {
            clean()
            updateModel {
                this.idEquip = idEquip
            }
        }

    override suspend fun setDetail(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel {
                this.detail = text
            }
        }

    override suspend fun getIdEquip(): Result<Int> =
        result(getClassAndMethod()) {
            readModel { ::idEquip.required() }
        }

    override suspend fun getDetail(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { detail }
        }

}