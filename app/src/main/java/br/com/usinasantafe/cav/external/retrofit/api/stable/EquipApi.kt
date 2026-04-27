package br.com.usinasantafe.cav.external.retrofit.api.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.EquipRetrofitModel
import br.com.usinasantafe.cav.lib.WEB_ALL_EQUIP
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface EquipApi {

    @GET(WEB_ALL_EQUIP)
    suspend fun all(@Header("Authorization") auth: String): Response<List<EquipRetrofitModel>>

}