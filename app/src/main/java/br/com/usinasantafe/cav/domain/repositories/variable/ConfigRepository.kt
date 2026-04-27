package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.utils.EmptyResult

interface ConfigRepository {
    suspend fun get(): Result<Config>
    suspend fun has(): Result<Boolean>
    suspend fun getPassword(): Result<String>
    suspend fun send(entity: Config): Result<Config>
    suspend fun save(entity: Config): EmptyResult
}