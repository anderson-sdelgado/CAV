package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.InsertCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IInsertCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): InsertCardRepository {

    override suspend fun setIdEquip(idEquip: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailEquip(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailEquipSecondary(text: String, idMain: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailColab(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailDriver(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailPassengerColab(text: String, idMain: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailVehicle(text: String): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailPassengerInvolved(text: String, idMain: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailInvolved(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailWitness(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setRegColab(regColab: Long): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStateColab(state: State): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStatePassengerColab(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStateDriver(state: State): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStateInvolved(state: State): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStatePassengerInvolved(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun setStateStateWitness(state: State): EmptyResult {
        TODO("Not yet implemented")
    }


}
