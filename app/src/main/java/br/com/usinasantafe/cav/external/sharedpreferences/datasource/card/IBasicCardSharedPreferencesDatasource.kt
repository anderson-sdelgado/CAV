package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.BasicCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject
import javax.inject.Provider

class IBasicCardSharedPreferencesDatasource @Inject constructor(
    private val cardSharedPreferencesDatasource: Provider<CardSharedPreferencesDatasource>
): BasicCardSharedPreferencesDatasource {

    override suspend fun setRegAttendant(regColab: Long): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                regAttendant = regColab
            }
        }

    override suspend fun setIdCar(idEquip: Int): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                idCar = idEquip
            }
        }

    override suspend fun setLocal(model: LocalSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                local = model
            }
        }

    override suspend fun listIdNature(): Result<List<Int>> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                idNatureList
            }
        }

    override suspend fun setIdNatureList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                idNatureList = idList
            }
        }

    override suspend fun getRegAttendant(): Result<Long> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                ::regAttendant.required()
            }
        }

    override suspend fun getIdCar(): Result<Int> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                ::idCar.required()
            }
        }

    override suspend fun listIdTypeAccident(): Result<List<Int>> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                idTypeAccidentList
            }
        }

    override suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                idTypeAccidentList = idList
            }
        }

    override suspend fun getLocal(): Result<LocalSharedPreferencesModel> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                local
            }
        }

    override suspend fun listIdDataLocal(): Result<List<Int>> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                idDataLocalList
            }
        }

    override suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                idDataLocalList = idList
            }
        }

    override suspend fun listIdSupportTeams(): Result<List<Int>> =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().readModel {
                idSupportTeamsList
            }
        }

    override suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            cardSharedPreferencesDatasource.get().updateModel {
                idSupportTeamsList = idList
            }
        }
}