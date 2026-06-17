package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.utils.EmptyResult

interface VehicleSharedPreferencesDatasource {
    suspend fun setDetail(text: String): EmptyResult
}