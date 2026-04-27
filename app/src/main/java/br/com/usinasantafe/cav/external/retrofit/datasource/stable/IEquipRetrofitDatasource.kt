package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.EquipApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.EquipRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.EquipRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IEquipRetrofitDatasource @Inject constructor(
    private val equipApi: EquipApi
): EquipRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<EquipRetrofitModel>> =
        result(getClassAndMethod()) {
            equipApi.all(token).body()!!
        }

}