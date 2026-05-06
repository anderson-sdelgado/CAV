package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.ROptionItemDataLocalApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ROptionItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ROptionItemDataLocalRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IROptionItemDataLocalRetrofitDatasource @Inject constructor(
    private val rOptionItemDataLocalApi: ROptionItemDataLocalApi
): ROptionItemDataLocalRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<ROptionItemDataLocalRetrofitModel>> =
        result(getClassAndMethod()) {
            rOptionItemDataLocalApi.all(token).body()!!
        }

}