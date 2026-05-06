package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.SupportTeamsApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.SupportTeamsRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.SupportTeamsRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class ISupportTeamsRetrofitDatasource @Inject constructor(
    private val supportTeamsApi: SupportTeamsApi
): SupportTeamsRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<SupportTeamsRetrofitModel>> =
        result(getClassAndMethod()) {
            supportTeamsApi.all(token).body()!!
        }

}