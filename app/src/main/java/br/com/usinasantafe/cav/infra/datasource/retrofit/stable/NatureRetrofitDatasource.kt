package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.NatureRetrofitModel

interface NatureRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<NatureRetrofitModel>>
}