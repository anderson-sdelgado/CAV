package br.com.usinasantafe.cav.domain.repositories.variable

interface ConfigRepository {
    suspend fun has(): Result<Boolean>
    suspend fun getPassword(): Result<String>
}