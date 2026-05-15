package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_CARD
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
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

    override suspend fun clean(): EmptyResult =
        result(getClassAndMethod()) {
            sharedPreferences.edit {
                putString(
                    BASE_SHARED_PREFERENCES_TABLE_CARD,
                    null
                )
            }
        }

    override suspend fun has(): Result<Boolean> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CARD,
                null
            )
            !data.isNullOrEmpty()
        }

    suspend fun get(): Result<CardSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CARD,
                null
            )
            if(data.isNullOrEmpty()) return@result CardSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                CardSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

    override suspend fun setRegAttendant(regColab: Long): EmptyResult =
        result(getClassAndMethod()) {
            val mainModel = get().getOrThrow()
            mainModel.regAttendant = regColab
            save(mainModel).getOrThrow()
        }

    override suspend fun setIdCar(idEquip: Int): EmptyResult =
        result(getClassAndMethod()) {
            val mainModel = get().getOrThrow()
            mainModel.idCar = idEquip
            save(mainModel).getOrThrow()
        }

    override suspend fun setLocal(model: LocalSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            val mainModel = get().getOrThrow()
            mainModel.local = model
            save(mainModel).getOrThrow()
        }

    override suspend fun listIdNature(): Result<List<Int>> =
        result(getClassAndMethod()) {
            get().getOrThrow().idNatureList
        }

    override suspend fun setIdNatureList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            val mainModel = get().getOrThrow()
            mainModel.idNatureList = idList
            save(mainModel).getOrThrow()
        }

    override suspend fun getRegAttendant(): Result<Long> =
        result(getClassAndMethod()) {
            get().getOrThrow()::regAttendant.required()
        }

    override suspend fun getIdCar(): Result<Int> =
        result(getClassAndMethod()) {
            get().getOrThrow()::idCar.required()
        }

    override suspend fun listIdTypeAccident(): Result<List<Int>> =
        result(getClassAndMethod()) {
            get().getOrThrow().idTypeAccidentList
        }

    override suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            val mainModel = get().getOrThrow()
            mainModel.idTypeAccidentList = idList
            save(mainModel).getOrThrow()
        }

    override suspend fun getLocal(): Result<LocalSharedPreferencesModel> =
        result(getClassAndMethod()) {
            get().getOrThrow().local
        }

    override suspend fun listIdDataLocal(): Result<List<Int>> =
        result(getClassAndMethod()) {
            get().getOrThrow().idDataLocalList
        }

}