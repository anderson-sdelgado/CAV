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

    override suspend fun deleteVehicleInvolved(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteVehicleInvolved(id).getOrThrow()
        }

    override suspend fun deleteInvolved(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteInvolved(id).getOrThrow()
        }

    override suspend fun deleteWitness(id: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deleteWitness(id).getOrThrow()
        }

    override suspend fun deletePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deletePassengerColab(idMain, idSecondary).getOrThrow()
        }

    override suspend fun deletePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.deletePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

}