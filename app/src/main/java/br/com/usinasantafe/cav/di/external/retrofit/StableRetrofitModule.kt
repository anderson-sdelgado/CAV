package br.com.usinasantafe.cav.di.external.retrofit

import br.com.usinasantafe.cav.external.retrofit.api.stable.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StableRetrofitModule {

    @Provides
    @Singleton
    fun colabApiRetrofit(
        retrofit: Retrofit
    ): ColabApi = retrofit.create(ColabApi::class.java)

    @Provides
    @Singleton
    fun equipApiRetrofit(
        retrofit: Retrofit
    ): EquipApi = retrofit.create(EquipApi::class.java)

}