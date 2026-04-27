package br.com.usinasantafe.cav.di.datasource.retrofit

import br.com.usinasantafe.cav.external.retrofit.datasource.stable.*
import br.com.usinasantafe.cav.infra.datasource.retrofit.stable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StableRetrofitDatasourceModule {

    @Binds
    @Singleton
    fun bindColabRetrofitDatasource(datasource: IColabRetrofitDatasource): ColabRetrofitDatasource

    @Binds
    @Singleton
    fun bindEquipRetrofitDatasource(datasource: IEquipRetrofitDatasource): EquipRetrofitDatasource

}