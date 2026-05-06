package br.com.usinasantafe.cav.external.retrofit.api.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.NatureRetrofitModel
import br.com.usinasantafe.cav.lib.WEB_ALL_NATURE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NatureApi {

    @GET(WEB_ALL_NATURE)
    suspend fun all(@Header("Authorization") auth: String): Response<List<NatureRetrofitModel>>

}