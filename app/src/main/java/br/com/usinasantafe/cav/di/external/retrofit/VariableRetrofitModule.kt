package br.com.usinasantafe.cav.di.external.retrofit

import br.com.usinasantafe.cav.external.retrofit.api.variable.ConfigApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VariableRetrofitModule {

    @Provides
    @Singleton
    fun configApiRetrofit(
        retrofit: Retrofit
    ): ConfigApi = retrofit.create(ConfigApi::class.java)

}