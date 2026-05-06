package br.com.usinasantafe.cav.external.retrofit.api.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.ROptionItemDataLocalRetrofitModel
import br.com.usinasantafe.cav.lib.WEB_ALL_R_OPTION_ITEM_DATA_LOCAL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ROptionItemDataLocalApi {

    @GET(WEB_ALL_R_OPTION_ITEM_DATA_LOCAL)
    suspend fun all(@Header("Authorization") auth: String): Response<List<ROptionItemDataLocalRetrofitModel>>

}