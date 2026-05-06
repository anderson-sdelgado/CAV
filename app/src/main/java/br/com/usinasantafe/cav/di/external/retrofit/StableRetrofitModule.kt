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

    @Provides
    @Singleton
    fun itemDataLocalApiRetrofit(
        retrofit: Retrofit
    ): ItemDataLocalApi = retrofit.create(ItemDataLocalApi::class.java)

    @Provides
    @Singleton
    fun natureApiRetrofit(
        retrofit: Retrofit
    ): NatureApi = retrofit.create(NatureApi::class.java)

    @Provides
    @Singleton
    fun optionDataLocalApiRetrofit(
        retrofit: Retrofit
    ): OptionDataLocalApi = retrofit.create(OptionDataLocalApi::class.java)

    @Provides
    @Singleton
    fun rOptionItemDataLocalApiRetrofit(
        retrofit: Retrofit
    ): ROptionItemDataLocalApi = retrofit.create(ROptionItemDataLocalApi::class.java)

    @Provides
    @Singleton
    fun supportTeamsApiRetrofit(
        retrofit: Retrofit
    ): SupportTeamsApi = retrofit.create(SupportTeamsApi::class.java)

    @Provides
    @Singleton
    fun typeAccidentApiRetrofit(
        retrofit: Retrofit
    ): TypeAccidentApi = retrofit.create(TypeAccidentApi::class.java)

}