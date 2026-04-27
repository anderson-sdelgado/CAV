package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.ColabApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ColabRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ColabRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IColabRetrofitDatasource @Inject constructor(
    private val colabApi: ColabApi
): ColabRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<ColabRetrofitModel>> =
        result(getClassAndMethod()) {
            colabApi.all(token).body()!!
        }

}