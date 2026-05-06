package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.ROptionItemDataLocalRetrofitModel

interface ROptionItemDataLocalRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<ROptionItemDataLocalRetrofitModel>>
}