package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.entityToSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
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

    override suspend fun listIdNature(): Result<List<Int>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listIdNature().getOrThrow()
        }

    override suspend fun setIdNatureList(idList: List<Int>): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.setIdNatureList(idList).getOrThrow()
        }

    override suspend fun getRegAttendant(): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegAttendant().getOrThrow()
        }

    override suspend fun getIdCar(): Result<Int> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getIdCar().getOrThrow()
        }

    override suspend fun listIdTypeAccident(): Result<List<Int>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listIdTypeAccident().getOrThrow()
        }

    override suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.setIdTypeAccidentList(idList).getOrThrow()
        }

    override suspend fun clean(): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.clean().getOrThrow()
        }

    override suspend fun getLocal(): Result<Local> =
        call(getClassAndMethod()) {
            val model = cardSharedPreferencesDatasource.getLocal().getOrThrow()
            model.sharedPreferencesModelToEntity()
        }

    override suspend fun listIdDataLocal(): Result<List<Int>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listIdDataLocal().getOrThrow()
        }

}