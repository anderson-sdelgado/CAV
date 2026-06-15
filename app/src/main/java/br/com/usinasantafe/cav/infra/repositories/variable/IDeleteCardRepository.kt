package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.DeleteCardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import javax.inject.Inject

class IDeleteCardRepository @Inject constructor(

): DeleteCardRepository {

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