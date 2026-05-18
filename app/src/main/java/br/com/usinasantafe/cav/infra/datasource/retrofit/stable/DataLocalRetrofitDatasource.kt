package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.DataLocalRetrofitModel

interface DataLocalRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<DataLocalRetrofitModel>>
}