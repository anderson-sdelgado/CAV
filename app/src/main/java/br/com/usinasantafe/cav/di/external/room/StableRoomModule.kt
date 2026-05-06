package br.com.usinasantafe.cav.di.external.room

import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.ROptionItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StableRoomModule {

    @Provides
    @Singleton
    fun provideColabDao(database: DatabaseRoom): ColabDao {
        return database.colabDao()
    }

    @Provides
    @Singleton
    fun provideEquipDao(database: DatabaseRoom): EquipDao {
        return database.equipDao()
    }

    @Provides
    @Singleton
    fun provideItemDataLocalDao(database: DatabaseRoom): ItemDataLocalDao {
        return database.itemDataLocalDao()
    }

    @Provides
    @Singleton
    fun provideNatureDao(database: DatabaseRoom): NatureDao {
        return database.natureDao()
    }

    @Provides
    @Singleton
    fun provideOptionDataLocalDao(database: DatabaseRoom): OptionDataLocalDao {
        return database.optionDataLocalDao()
    }

    @Provides
    @Singleton
    fun provideROptionItemDataLocalDao(database: DatabaseRoom): ROptionItemDataLocalDao {
        return database.rOptionItemDataLocalDao()
    }

    @Provides
    @Singleton
    fun provideSupportTeamsDao(database: DatabaseRoom): SupportTeamsDao {
        return database.supportTeamsDao()
    }

    @Provides
    @Singleton
    fun provideTypeAccidentDao(database: DatabaseRoom): TypeAccidentDao {
        return database.typeAccidentDao()
    }

}