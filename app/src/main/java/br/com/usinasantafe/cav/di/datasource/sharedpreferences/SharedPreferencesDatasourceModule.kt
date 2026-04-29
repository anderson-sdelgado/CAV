package br.com.usinasantafe.cav.di.datasource.sharedpreferences

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.*
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SharedPreferencesDatasourceModule {

    @Binds
    @Singleton
    fun bindConfigSharedPreferencesDatasource(dataSource: IConfigSharedPreferencesDatasource): ConfigSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindCardSharedPreferencesDatasource(dataSource: ICardSharedPreferencesDatasource): CardSharedPreferencesDatasource

}