package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.infra.datasource.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IConfigRepository @Inject constructor(
    private val configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource
): ConfigRepository {

    override suspend fun has(): Result<Boolean> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.has().getOrThrow()
        }

    override suspend fun getPassword(): Result<String> =
        call(getClassAndMethod()) {
            configSharedPreferencesDatasource.getPassword().getOrThrow()
        }
}