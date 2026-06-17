package br.com.usinasantafe.cav.external.sharedpreferences.datasource

import android.content.SharedPreferences
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IVehicleSharedPreferencesDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
): VehicleSharedPreferencesDatasource {
    override suspend fun setDetail(text: String): EmptyResult {
        TODO("Not yet implemented")
    }
}