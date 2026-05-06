package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.SupportTeamsRetrofitModel

interface SupportTeamsRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<SupportTeamsRetrofitModel>>
}