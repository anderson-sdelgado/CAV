package br.com.usinasantafe.cav.external.retrofit.api.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.TypeAccidentRetrofitModel
import br.com.usinasantafe.cav.lib.WEB_ALL_TYPE_ACCIDENT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TypeAccidentApi {

    @GET(WEB_ALL_TYPE_ACCIDENT)
    suspend fun all(@Header("Authorization") auth: String): Response<List<TypeAccidentRetrofitModel>>

}