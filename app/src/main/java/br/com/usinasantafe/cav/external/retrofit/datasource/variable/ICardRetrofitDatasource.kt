package br.com.usinasantafe.cav.external.retrofit.datasource.variable

import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.CardRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.CardRetrofitModelOutput
import javax.inject.Inject

class ICardRetrofitDatasource @Inject constructor(

): CardRetrofitDatasource {

    override suspend fun send(
        token: String,
        modelList: CardRetrofitModelOutput
    ): Result<CardRetrofitModelOutput> {
        TODO("Not yet implemented")
    }

}