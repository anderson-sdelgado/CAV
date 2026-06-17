package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IEquipSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): EquipSharedPreferencesDatasource {

    override suspend fun setIdEquip(idEquip: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetail(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Result<EquipSharedPreferencesModel> {
        TODO("Not yet implemented")
    }

    override suspend fun clean(): EmptyResult {
        TODO("Not yet implemented")
    }
}