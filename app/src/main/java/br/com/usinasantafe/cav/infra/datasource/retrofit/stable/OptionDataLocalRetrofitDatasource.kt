package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.OptionDataLocalRetrofitModel

interface OptionDataLocalRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<OptionDataLocalRetrofitModel>>
}