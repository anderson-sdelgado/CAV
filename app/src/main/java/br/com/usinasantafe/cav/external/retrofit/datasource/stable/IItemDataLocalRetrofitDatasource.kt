package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.ItemDataLocalApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.ItemDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.ItemDataLocalRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IItemDataLocalRetrofitDatasource @Inject constructor(
    private val itemDataLocalApi: ItemDataLocalApi
): ItemDataLocalRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<ItemDataLocalRetrofitModel>> =
        result(getClassAndMethod()) {
            itemDataLocalApi.all(token).body()!!
        }

}