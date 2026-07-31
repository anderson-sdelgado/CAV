package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.UpdateCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IUpdateCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): UpdateCardRepository {

    override suspend fun updateIdEquip(idEquip: Int, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateIdEquip(idEquip, id).getOrThrow()
        }

    override suspend fun updateIdEquipSecondary(
        idEquip: Int,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateIdEquipSecondary(idEquip, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDetailEquip(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailEquip(text, id).getOrThrow()
        }

    override suspend fun updateDetailEquipSecondary(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailEquipSecondary(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDetailColab(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailColab(text, id).getOrThrow()
        }

    override suspend fun updateDetailDriver(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailDriver(text, id).getOrThrow()
        }

    override suspend fun updateDetailPassengerColab(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailPassengerColab(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDetailVehicle(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailVehicle(text, id).getOrThrow()
        }

    override suspend fun updateDetailPassengerExternal(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailPassengerExternal(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDetailInvolvedExternal(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailInvolvedExternal(text, id).getOrThrow()
        }

    override suspend fun updateDetailWitnessExternal(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailWitnessExternal(text, id).getOrThrow()
        }

    override suspend fun updateDetailInvolvedColab(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailInvolvedColab(text, id).getOrThrow()
        }

    override suspend fun updateDetailWitnessColab(text: String, id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDetailWitnessColab(text, id).getOrThrow()
        }

    override suspend fun updateRegColab(regColab: Long, idMain: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateRegColab(regColab, idMain).getOrThrow()
        }

    override suspend fun updateRegPassengerColab(
        regColab: Long,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateRegPassengerColab(regColab, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateRegColabInvolved(
        regColab: Long,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateRegColabInvolved(regColab, idMain).getOrThrow()
        }

    override suspend fun updateRegColabWitness(
        regColab: Long,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateRegColabWitness(regColab, idMain).getOrThrow()
        }

    override suspend fun updateStateColab(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateColab(state, idMain).getOrThrow()
        }

    override suspend fun updateStatePassengerColab(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStatePassengerColab(state, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateStateDriver(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateDriver(state, idMain).getOrThrow()
        }

    override suspend fun updateStatePassengerExternal(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStatePassengerExternal(state, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateStateInvolvedExternal(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateInvolvedExternal(state, idMain).getOrThrow()
        }

    override suspend fun updateStateWitnessExternal(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateWitnessExternal(state, idMain).getOrThrow()
        }

    override suspend fun updateStateInvolvedColab(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateInvolvedColab(state, idMain).getOrThrow()
        }


    override suspend fun updateStateWitnessColab(
        state: State,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateStateWitnessColab(state, idMain).getOrThrow()
        }

    override suspend fun updateAddressPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateAddressPassengerInvolved(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateAddressInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateAddressInvolved(text, idMain).getOrThrow()
        }

    override suspend fun updateAddressDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateAddressDriver(text, idMain).getOrThrow()
        }

    override suspend fun updateBrand(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateBrand(text, idMain).getOrThrow()
        }

    override suspend fun updatePlate(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updatePlate(text, idMain).getOrThrow()
        }

    override suspend fun updateDocumentDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDocumentDriver(text, idMain).getOrThrow()
        }

    override suspend fun updateDocumentPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDocumentPassengerInvolved(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDocumentInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDocumentInvolved(text, idMain).getOrThrow()
        }

    override suspend fun updateNameDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateNameDriver(text, idMain).getOrThrow()
        }

    override suspend fun updateNamePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateNamePassengerInvolved(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateNameInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateNameInvolved(text, idMain).getOrThrow()
        }

    override suspend fun updateNameWitness(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateNameWitness(text, idMain).getOrThrow()
        }

    override suspend fun updatePhoneDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updatePhoneDriver(text, idMain).getOrThrow()
        }

    override suspend fun updatePhoneInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updatePhoneInvolved(text, idMain).getOrThrow()
        }

    override suspend fun updatePhoneWitness(
        text: String,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updatePhoneWitness(text, idMain).getOrThrow()
        }

    override suspend fun updatePhonePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updatePhonePassengerInvolved(text, idMain, idSecondary).getOrThrow()
        }

    override suspend fun updateDataInitialBreathalyzer(
        flagRealized: Boolean?,
        flagResult: Boolean?,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateDataInitialBreathalyzer(flagRealized, flagResult, idMain).getOrThrow()
        }

    override suspend fun updateCountBreathalyzer(
        count: Double?,
        idMain: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.updateCountBreathalyzer(count, idMain).getOrThrow()
        }

}
