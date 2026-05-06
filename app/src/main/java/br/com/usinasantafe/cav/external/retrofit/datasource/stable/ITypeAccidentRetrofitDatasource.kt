package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.external.retrofit.api.stable.TypeAccidentApi
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.TypeAccidentRetrofitDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.stable.TypeAccidentRetrofitModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject

class ITypeAccidentRetrofitDatasource @Inject constructor(
    private val typeAccidentApi: TypeAccidentApi
): TypeAccidentRetrofitDatasource {

    override suspend fun listAll(token: String): Result<List<TypeAccidentRetrofitModel>> =
        result(getClassAndMethod()) {
            typeAccidentApi.all(token).body()!!
        }

}