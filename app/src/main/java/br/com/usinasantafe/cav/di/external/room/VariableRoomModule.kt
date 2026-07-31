package br.com.usinasantafe.cav.di.external.room

import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.*
import br.com.usinasantafe.cav.external.room.dao.variable.PassengerExternalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VariableRoomModule {

    @Provides
    @Singleton
    fun provideCardDao(database: DatabaseRoom): CardDao {
        return database.cardDao()
    }

    @Provides
    @Singleton
    fun provideColabCardDao(database: DatabaseRoom): PassengerColabDao {
        return database.colabCardDao()
    }

    @Provides
    @Singleton
    fun provideEquipCardDao(database: DatabaseRoom): EquipSecDao {
        return database.equipCardDao()
    }

    @Provides
    @Singleton
    fun provideInvolvedExternalDao(database: DatabaseRoom): InvolvedExternalDao {
        return database.involvedExternalDao()
    }

    @Provides
    @Singleton
    fun provideVehicleExternalDao(database: DatabaseRoom): VehicleExternalDao {
        return database.vehicleExternalDao()
    }

    @Provides
    @Singleton
    fun provideVehicleOwnDao(database: DatabaseRoom): VehicleOwnDao {
        return database.vehicleOwnDao()
    }

    @Provides
    @Singleton
    fun providePassengerExternalDao(database: DatabaseRoom): PassengerExternalDao {
        return database.passengerExternalDao()
    }

    @Provides
    @Singleton
    fun provideWitnessExternalDao(database: DatabaseRoom): WitnessExternalDao {
        return database.witnessExternalDao()
    }

    @Provides
    @Singleton
    fun provideInvolvedColabDao(database: DatabaseRoom): InvolvedColabDao {
        return database.involvedColabDao()
    }

    @Provides
    @Singleton
    fun provideWitnessColabDao(database: DatabaseRoom): WitnessColabDao {
        return database.witnessColabDao()
    }
}

