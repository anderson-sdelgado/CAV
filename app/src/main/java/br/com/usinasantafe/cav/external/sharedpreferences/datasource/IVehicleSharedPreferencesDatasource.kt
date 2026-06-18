package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IVehicleSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): VehicleSharedPreferencesDatasource {

    override suspend fun setDetail(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Result<VehicleSharedPreferencesModel> {
        TODO("Not yet implemented")
    }

    override suspend fun clean(): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setBrand(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setPlate(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

}