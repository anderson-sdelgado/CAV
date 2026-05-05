package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.entityToSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class ICardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource
): CardRepository {

    override suspend fun setRegAttendant(regColab: Long): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.setRegAttendant(regColab).getOrThrow()
        }

    override suspend fun setIdCar(idEquip: Int): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.setIdCar(idEquip).getOrThrow()
        }

    override suspend fun setLocal(entity: Local): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.setLocal(entity.entityToSharedPreferencesModel()).getOrThrow()
        }

}