package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.UpdateCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IUpdateCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): UpdateCardRepository {

    override suspend fun updateIdEquip(idEquip: Int, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateIdEquipSecondary(
        idEquip: Int,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailEquip(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailEquipSecondary(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailColab(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailDriver(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailPassengerColab(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailVehicle(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailInvolved(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDetailWitness(text: String, id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateRegColab(regColab: Long, idMain: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateRegPassengerColab(
        regColab: Long,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStateColab(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStatePassengerColab(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStateDriver(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStatePassengerInvolved(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStateInvolved(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateStateWitness(
        state: State,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateAddressPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateAddressInvolved(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateAddressDriver(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateBrand(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updatePlate(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDocumentDriver(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDocumentPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateDocumentInvolved(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateNameDriver(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateNamePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateNameInvolved(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateNameWitness(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhoneDriver(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhoneInvolved(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhoneWitness(
        text: String,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhonePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

}
