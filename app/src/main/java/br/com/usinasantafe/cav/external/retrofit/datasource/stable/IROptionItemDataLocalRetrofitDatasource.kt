package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.ROptionItemDataLocalApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.DataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.DataLocalRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IROptionItemDataLocalRetrofitDatasource @Inject constructor(
    private val rOptionItemDataLocalApi: ROptionItemDataLocalApi
): DataLocalRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<DataLocalRetrofitModel>> =
        result(getClassAndMethod()) {
            rOptionItemDataLocalApi.all(token).body()!!
        }

}