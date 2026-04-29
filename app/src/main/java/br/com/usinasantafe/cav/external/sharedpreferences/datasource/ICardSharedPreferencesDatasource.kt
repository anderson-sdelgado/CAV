package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_CARD
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class ICardSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): CardSharedPreferencesDatasource {

    suspend fun save(model: CardSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_CARD,
                    Gson().toJson(model)
                )
            }
        }

    suspend fun get(): Result<CardSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val config = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CARD,
                null
            )
            if(config.isNullOrEmpty()) return@result CardSharedPreferencesModel()
            val model = Gson().fromJson(
                config,
                CardSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun setRegAttendant(regColab: Long): EmptyResult =
        result(getClassAndMethod()) {
            val model = get().getOrThrow()
            model.regAttendant = regColab
            save(model).getOrThrow()
        }

    override suspend fun setIdCar(idEquip: Int): EmptyResult =
        result(getClassAndMethod()) {
            val model = get().getOrThrow()
            model.idCar = idEquip
            save(model).getOrThrow()
        }

}