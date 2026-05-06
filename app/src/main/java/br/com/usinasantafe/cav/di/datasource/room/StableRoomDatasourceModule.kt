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

    @Binds
    @Singleton
    fun bindItemDataLocalRoomDatasource(datasource: IItemDataLocalRoomDatasource): ItemDataLocalRoomDatasource

    @Binds
    @Singleton
    fun bindNatureRoomDatasource(datasource: INatureRoomDatasource): NatureRoomDatasource

    @Binds
    @Singleton
    fun bindOptionDataLocalRoomDatasource(datasource: IOptionDataLocalRoomDatasource): OptionDataLocalRoomDatasource

    @Binds
    @Singleton
    fun bindROptionItemDataLocalRoomDatasource(datasource: IROptionItemDataLocalRoomDatasource): ROptionItemDataLocalRoomDatasource

    @Binds
    @Singleton
    fun bindSupportTeamsRoomDatasource(datasource: ISupportTeamsRoomDatasource): SupportTeamsRoomDatasource

    @Binds
    @Singleton
    fun bindTypeAccidentRoomDatasource(datasource: ITypeAccidentRoomDatasource): TypeAccidentRoomDatasource

}