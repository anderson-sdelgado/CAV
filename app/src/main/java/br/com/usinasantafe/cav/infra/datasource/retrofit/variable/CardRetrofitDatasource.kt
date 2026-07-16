package br.com.usinasantafe.cav.infra.datasource.retrofit.variable

import br.com.usinasantafe.cav.infra.models.retrofit.variable.CardRetrofitModelOutput

interface CardRetrofitDatasource {
    suspend fun send(
        token: String,
        modelList: CardRetrofitModelOutput
    ): Result<CardRetrofitModelOutput>
}