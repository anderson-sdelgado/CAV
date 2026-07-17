package br.com.usinasantafe.cav.external.retrofit.api.variable

import br.com.usinasantafe.cav.infra.models.retrofit.variable.CardRetrofitModelInput
import br.com.usinasantafe.cav.lib.WEB_SAVE_CARD
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CardApi {

    @Multipart
    @POST(WEB_SAVE_CARD)
    suspend fun send(
        @Header("Authorization") auth: String,
        @Part("card")
        card: RequestBody,
        @Part("photos")
        photos: List<MultipartBody.Part>
    ): Response<CardRetrofitModelInput>
}