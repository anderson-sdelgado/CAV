package br.com.usinasantafe.cav.external.retrofit.datasource.variable

import android.content.Context
import android.util.Log
import br.com.usinasantafe.cav.external.retrofit.api.variable.CardApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.CardRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.CardRetrofitModelInput
import br.com.usinasantafe.cav.infra.models.retrofit.variable.CardRetrofitModelOutput
import br.com.usinasantafe.cav.infra.models.retrofit.variable.retrofitToEntity
import br.com.usinasantafe.cav.utils.compressImage
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ICardRetrofitDatasource @Inject constructor(
    private val cardApi: CardApi,
    @param:ApplicationContext
    private val context: Context,
): CardRetrofitDatasource {

    private val gson = Gson()

    override suspend fun send(
        token: String,
        model: CardRetrofitModelOutput
    ): Result<CardRetrofitModelInput> =
        result(getClassAndMethod()) {
            val card =
                gson.toJson(model)
                    .toRequestBody("application/json".toMediaType())

            val compressedFiles = mutableListOf<File>()

            val photos = model.urlPhotoList.map { path ->

                val original = File(path)
                val file = compressImage(context, original)

                compressedFiles.add(file)

                MultipartBody.Part.createFormData(
                    "photos[]",
                    file.name,
                    file.asRequestBody("image/jpeg".toMediaType())
                )
            }

            try {

                val model = cardApi.send(token, card, photos).body()!!
                model.retrofitToEntity()
                model

            } finally {

                compressedFiles.forEach { file ->
                    if (file.exists()) {
                        file.delete()
                    }
                }
            }
        }

}