package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
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

    override suspend fun getRegColab(id: Int): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun getStateColab(id: Int): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getStatePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getStateWitness(id: Int): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getStatePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getStateInvolved(id: Int): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getStateDriver(id: Int): Result<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddressPassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddressDriver(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddressInvolved(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getBrand(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDocumentDriver(idMain: Int): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDocumentPassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getNameDriver(idMain: Int): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getNamePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun listIdEquipSecondary(idMain: Int): Result<List<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun listRegPassengerColab(idMain: Int): Result<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun listPassengerInvolved(idMain: Int): Result<List<Involved>> {
        TODO("Not yet implemented")
    }

    override suspend fun listInvolved(): Result<List<Involved>> {
        TODO("Not yet implemented")
    }

    override suspend fun listWitness(): Result<List<Involved>> {
        TODO("Not yet implemented")
    }

}
