package br.com.usinasantafe.cav.infra.datasource

interface ConfigSharedPreferencesDatasource {
    suspend fun has(): Result<Boolean>
    suspend fun getPassword(): Result<String>
}