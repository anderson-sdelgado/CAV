package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.DeleteCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IDeleteCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): DeleteCardRepository {

    override suspend fun deleteVehicleOwn(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteVehicleOwn(id).getOrThrow()
        }

    override suspend fun deleteEquipSecondary(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteEquipSecondary(idMain, idSecondary).getOrThrow()
        }

    override suspend fun deleteVehicleExternal(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteVehicleExternal(id).getOrThrow()
        }

    override suspend fun deleteInvolvedExternal(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteInvolvedExternal(id).getOrThrow()
        }

    override suspend fun deleteWitnessExternal(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteWitnessExternal(id).getOrThrow()
        }

    override suspend fun deleteInvolvedColab(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteInvolvedColab(id).getOrThrow()
        }

    override suspend fun deleteWitnessColab(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteWitnessColab(id).getOrThrow()
        }

    override suspend fun deletePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deletePassengerColab(idMain, idSecondary).getOrThrow()
        }

    override suspend fun deletePassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deletePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun deletePhoto(url: String): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deletePhoto(url).getOrThrow()
        }

}