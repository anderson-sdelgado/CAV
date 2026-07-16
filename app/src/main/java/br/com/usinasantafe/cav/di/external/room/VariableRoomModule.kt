package br.com.usinasantafe.cav.di.external.room

import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.*
import br.com.usinasantafe.cav.external.room.dao.variable.PassengerInvolvedDao
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
    fun provideInvolvedDao(database: DatabaseRoom): InvolvedDao {
        return database.involvedDao()
    }

    @Provides
    @Singleton
    fun provideVehicleInvolvedDao(database: DatabaseRoom): VehicleInvolvedDao {
        return database.vehicleInvolvedDao()
    }

    @Provides
    @Singleton
    fun provideVehicleOwnDao(database: DatabaseRoom): VehicleOwnDao {
        return database.vehicleOwnDao()
    }

    @Provides
    @Singleton
    fun providePassengerInvolvedDao(database: DatabaseRoom): PassengerInvolvedDao {
        return database.passengerInvolvedDao()
    }

    @Provides
    @Singleton
    fun provideWitnessDao(database: DatabaseRoom): WitnessDao {
        return database.witnessDao()
    }

}