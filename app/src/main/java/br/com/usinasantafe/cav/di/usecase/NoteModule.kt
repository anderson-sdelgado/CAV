package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.common.HasRegColab
import br.com.usinasantafe.cav.domain.usecases.common.IHasRegColab
import br.com.usinasantafe.cav.domain.usecases.note.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NoteModule {

    @Binds
    @Singleton
    fun bindHasRegColab(usecase: IHasRegColab): HasRegColab

    @Binds
    @Singleton
    fun bindSetRegAttendant(usecase: ISetRegAttendant): SetRegAttendant

}