package br.com.usinasantafe.cav.di.datasource.room

import br.com.usinasantafe.cav.external.room.datasource.variable.*
import br.com.usinasantafe.cav.infra.datasource.room.variable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableRoomDatasourceModule {

    @Binds
    @Singleton
    fun bindCardRoomDatasource(datasource: ICardRoomDatasource): CardRoomDatasource

    @Binds
    @Singleton
    fun bindPassengerColabRoomDatasource(datasource: IPassengerColabRoomDatasource): PassengerColabRoomDatasource

    @Binds
    @Singleton
    fun bindEquipSecRoomDatasource(datasource: IEquipSecRoomDatasource): EquipSecRoomDatasource

    @Binds
    @Singleton
    fun bindInvolvedRoomDatasource(datasource: IInvolvedRoomDatasource): InvolvedRoomDatasource

    @Binds
    @Singleton
    fun bindVehicleInvolvedRoomDatasource(datasource: IVehicleInvolvedRoomDatasource): VehicleInvolvedRoomDatasource

    @Binds
    @Singleton
    fun bindVehicleOwnRoomDatasource(datasource: IVehicleOwnRoomDatasource): VehicleOwnRoomDatasource

    @Binds
    @Singleton
    fun bindWitnessRoomDatasource(datasource: IWitnessRoomDatasource): WitnessRoomDatasource

    @Binds
    @Singleton
    fun bindPassengerInvolvedRoomDatasource(datasource: IPassengerInvolvedRoomDatasource): PassengerInvolvedRoomDatasource

}