package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.ConfigRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.entityToRetrofitModel
import br.com.usinasantafe.cav.infra.models.retrofit.variable.retrofitToEntity
import br.com.usinasantafe.cav.infra.models.sharedpreferences.entityToSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.FlagUpdate
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.EmptyResult
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

    override suspend fun save(entity: Config): EmptyResult =
        call(getClassAndMethod()) {
            val sharedPreferencesModel = entity.entityToSharedPreferencesModel()
            configSharedPreferencesDatasource.save(sharedPreferencesModel).getOrThrow()
        }

    override suspend fun getFlagUpdate(): Result<Boolean> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.getFlagUpdate().getOrThrow()
        }

    override suspend fun setFlagUpdate(): EmptyResult =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.setFlagUpdate().getOrThrow()
        }

    override suspend fun getStatusSend(): Result<StatusSend> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.getStatusSend().getOrThrow()
        }

}