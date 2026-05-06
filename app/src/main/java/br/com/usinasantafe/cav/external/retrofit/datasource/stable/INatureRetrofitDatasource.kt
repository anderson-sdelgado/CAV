package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.NatureApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.NatureRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.NatureRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class INatureRetrofitDatasource @Inject constructor(
    private val natureApi: NatureApi
): NatureRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<NatureRetrofitModel>> =
        result(getClassAndMethod()) {
            natureApi.all(token).body()!!
        }

}