package br.com.usinasantafe.cav.di.datasource.room

import br.com.usinasantafe.cav.external.room.datasource.stable.*
import br.com.usinasantafe.cav.infra.datasource.room.stable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StableRoomDatasourceModule {

    @Binds
    @Singleton
    fun bindColabRoomDatasource(datasource: IColabRoomDatasource): ColabRoomDatasource

    @Binds
    @Singleton
    fun bindEquipRoomDatasource(datasource: IEquipRoomDatasource): EquipRoomDatasource

}