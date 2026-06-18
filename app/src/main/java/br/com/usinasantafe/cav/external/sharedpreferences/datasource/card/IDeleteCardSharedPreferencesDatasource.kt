package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.DeleteCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IDeleteCardSharedPreferencesDatasource @Inject constructor(

): DeleteCardSharedPreferencesDatasource {
    override suspend fun deleteVehicleOwn(id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEquipSecondary(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVehicleInvolved(id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInvolved(id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWitness(id: Int): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deletePassengerColab(
        idSelection: Int,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }

    override suspend fun deletePassengerInvolved(
        idSelection: Int,
        idMain: Int
    ): EmptyResult {
        TODO("Not yet implemented")
    }
}