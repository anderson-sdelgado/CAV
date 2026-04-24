package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.ConfigRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.entityToRetrofitModel
import br.com.usinasantafe.cav.infra.models.retrofit.variable.retrofitToEntity
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject
import kotlin.text.get

class IConfigRepository @Inject constructor(
    private val configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource,
    private val configRetrofitDatasource: ConfigRetrofitDatasource
): ConfigRepository {

    override suspend fun get(): Result<Config> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.get().getOrThrow().sharedPreferencesModelToEntity()
        }

    override suspend fun has(): Result<Boolean> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.has().getOrThrow()
        }

    override suspend fun getPassword(): Result<String> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.getPassword().getOrThrow()
        }

    override suspend fun send(entity: Config): Result<Config> =
        call(getClassAndMethod()) {
            val model = entity.entityToRetrofitModel()
            val configRetrofitModel = configRetrofitDatasource.recoverToken(model).getOrThrow()
            configRetrofitModel.retrofitToEntity()
        }
}