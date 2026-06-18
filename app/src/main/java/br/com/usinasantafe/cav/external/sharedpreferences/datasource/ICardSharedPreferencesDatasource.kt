package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IBasicCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IDeleteCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IInsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IRecoverDataCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.IUpdateCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.BasicCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.DeleteCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.RecoverDataCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.UpdateCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.BASE_SHARED_PREFERENCES_TABLE_CARD
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import javax.inject.Inject

class ICardSharedPreferencesDatasource @Inject constructor(
    private val basicCardSharedPreferencesDatasource: IBasicCardSharedPreferencesDatasource,
    private val insertCardSharedPreferencesDatasource: IInsertCardSharedPreferencesDatasource,
    private val recoverDataCardSharedPreferencesDatasource: IRecoverDataCardSharedPreferencesDatasource,
    private val updateCardSharedPreferencesDatasource: IUpdateCardSharedPreferencesDatasource,
    private val deleteCardSharedPreferencesDatasource: IDeleteCardSharedPreferencesDatasource,
    private val sharedPreferences: SharedPreferences
): CardSharedPreferencesDatasource,
    BasicCardSharedPreferencesDatasource by basicCardSharedPreferencesDatasource,
    InsertCardSharedPreferencesDatasource by insertCardSharedPreferencesDatasource,
    RecoverDataCardSharedPreferencesDatasource by recoverDataCardSharedPreferencesDatasource,
    UpdateCardSharedPreferencesDatasource by updateCardSharedPreferencesDatasource,
    DeleteCardSharedPreferencesDatasource by deleteCardSharedPreferencesDatasource {

    override suspend fun updateModel(block: CardSharedPreferencesModel.() -> Unit) {
        val model = get().getOrThrow()
        model.block()
        save(model).getOrThrow()
    }

    override suspend fun <T> readModel(
        block: CardSharedPreferencesModel.() -> T
    ): T =
        get()
            .getOrThrow()
            .block()

    override suspend fun save(model: CardSharedPreferencesModel): EmptyResult =
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

    override suspend fun get(): Result<CardSharedPreferencesModel> =
        result(getClassAndMethod()) {
            val data = sharedPreferences.getString(
                BASE_SHARED_PREFERENCES_TABLE_CARD,
                null
            )
            if (data.isNullOrEmpty()) return@result CardSharedPreferencesModel()
            val model = Gson().fromJson(
                data,
                CardSharedPreferencesModel::class.java
            )
            model.sharedPreferencesModelToEntity()
            model
        }

}
