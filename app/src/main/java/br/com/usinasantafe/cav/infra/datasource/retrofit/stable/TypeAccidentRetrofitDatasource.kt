package br.com.usinasantafe.cav.infra.datasource.retrofit.stable

import br.com.usinasantafe.cav.infra.models.retrofit.stable.TypeAccidentRetrofitModel

interface TypeAccidentRetrofitDatasource {
    suspend fun listAll(token: String): Result<List<TypeAccidentRetrofitModel>>
}