package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface VehicleSharedPreferencesDatasource {
    suspend fun get(): Result<VehicleSharedPreferencesModel>
    suspend fun clean(): EmptyResult
    suspend fun setPlate(text: String): EmptyResult
    suspend fun setBrand(text: String): EmptyResult
    suspend fun setDetail(text: String): EmptyResult
    suspend fun getPlate(): Result<String?>
    suspend fun getBrand(): Result<String?>
    suspend fun getDetail(): Result<String?>
}