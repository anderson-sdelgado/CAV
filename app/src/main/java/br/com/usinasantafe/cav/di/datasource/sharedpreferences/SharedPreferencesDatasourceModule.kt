package br.com.usinasantafe.cav.di.datasource.sharedpreferences

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.*
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.*
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
    fun bindColabSharedPreferencesDatasource(dataSource: IColabSharedPreferencesDatasource): ColabSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindEquipSharedPreferencesDatasource(dataSource: IEquipSharedPreferencesDatasource): EquipSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindVehicleSharedPreferencesDatasource(dataSource: IVehicleSharedPreferencesDatasource): VehicleSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindInvolvedSharedPreferencesDatasource(dataSource: IInvolvedSharedPreferencesDatasource): InvolvedSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindCardSharedPreferencesDatasource(dataSource: ICardSharedPreferencesDatasource): CardSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindBasicCardSharedPreferencesDatasource(dataSource: IBasicCardSharedPreferencesDatasource): BasicCardSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindInsertCardSharedPreferencesDatasource(dataSource: IInsertCardSharedPreferencesDatasource): InsertCardSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindRecoverDataCardSharedPreferencesDatasource(dataSource: IRecoverDataCardSharedPreferencesDatasource): RecoverDataCardSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindUpdateCardSharedPreferencesDatasource(dataSource: IUpdateCardSharedPreferencesDatasource): UpdateCardSharedPreferencesDatasource

    @Binds
    @Singleton
    fun bindDeleteCardSharedPreferencesDatasource(dataSource: IDeleteCardSharedPreferencesDatasource): DeleteCardSharedPreferencesDatasource

}