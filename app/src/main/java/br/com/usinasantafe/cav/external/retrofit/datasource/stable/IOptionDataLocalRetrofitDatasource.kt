package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.OptionDataLocalApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.OptionDataLocalRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.OptionDataLocalRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class IOptionDataLocalRetrofitDatasource @Inject constructor(
    private val optionDataLocalApi: OptionDataLocalApi
): OptionDataLocalRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<OptionDataLocalRetrofitModel>> =
        result(getClassAndMethod()) {
            optionDataLocalApi.all(token).body()!!
        }

}