package br.com.usinasantafe.cav.external.retrofit.datasource.variable

import br.com.usinasantafe.cav.external.retrofit.api.variable.ConfigApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.ConfigRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.ConfigRetrofitModelInput
import br.com.usinasantafe.cav.infra.models.retrofit.variable.ConfigRetrofitModelOutput
import br.com.usinasantafe.cav.infra.models.retrofit.variable.retrofitToEntity
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IConfigRetrofitDatasource @Inject constructor(
    private val configApi: ConfigApi,
): ConfigRetrofitDatasource {

    override suspend fun recoverToken(
        retrofitModelOutput: ConfigRetrofitModelOutput
    ): Result<ConfigRetrofitModelInput> =
        result(getClassAndMethod()) {
            val model = configApi.send(retrofitModelOutput).body()!!
            model.retrofitToEntity()
            model
        }

}