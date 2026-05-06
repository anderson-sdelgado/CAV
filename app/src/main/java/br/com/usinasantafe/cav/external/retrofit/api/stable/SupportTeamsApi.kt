package br.com.usinasantafe.cav.external.retrofit.api.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.SupportTeamsRetrofitModel
import br.com.usinasantafe.cav.lib.WEB_ALL_SUPPORT_TEAMS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SupportTeamsApi {

    @GET(WEB_ALL_SUPPORT_TEAMS)
    suspend fun all(@Header("Authorization") auth: String): Response<List<SupportTeamsRetrofitModel>>

}