package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_VEHICLE
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class IVehicleSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): VehicleSharedPreferencesDatasource {

    suspend fun updateModel(block: VehicleSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    suspend fun <T> readModel(
        block: VehicleSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    suspend fun save(model: VehicleSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_VEHICLE,
                    Gson().toJson(model)
                )
            }
        }

    override suspend fun get(): Result<VehicleSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_VEHICLE,
                null
            )
            if (data.isNullOrEmpty()) return@result VehicleSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                VehicleSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun clean(): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_VEHICLE,
                    null
                )
            }
        }

    override suspend fun setPlate(text: String): EmptyResult =
        result(getClassAndMethod()) {
            clean()
            updateModel { plate = text }
        }

    override suspend fun setBrand(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { brand = text }
        }

    override suspend fun setDetail(text: String): EmptyResult =
        result(getClassAndMethod()) {
            updateModel { detail = text }
        }

    override suspend fun getPlate(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { plate }
        }

    override suspend fun getBrand(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { brand }
        }

    override suspend fun getDetail(): Result<String?> =
        result(getClassAndMethod()) {
            readModel { detail }
        }

}