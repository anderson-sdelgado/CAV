package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.ColabRetrofitModel

interface ColabRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<ColabRetrofitModel>>
}