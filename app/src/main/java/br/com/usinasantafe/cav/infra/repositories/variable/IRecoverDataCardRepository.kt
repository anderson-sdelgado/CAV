package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import javax.inject.Inject

class IRecoverDataCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): RecoverDataCardRepository {

    override suspend fun getIdEquip(id: Int): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getIdEquipSecondary(idMain: Int, idSecondary: Int): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailEquip(id: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailEquipSecondary(idMain: Int, idSecondary: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailColab(id: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailPassengerColab(idMain: Int, idSecondary: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailVehicle(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailDriver(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailInvolved(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailWitness(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getRegColab(id: Int): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Int> {
        TODO("Not yet implemented")
    }

}
