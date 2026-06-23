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
    private val datasource: Provider<CardSharedPreferencesDatasource>
): BasicCardSharedPreferencesDatasource {

    override suspend fun setRegAttendant(regColab: Long): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                regAttendant = regColab
            }
        }

    override suspend fun setIdCar(idEquip: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                idCar = idEquip
            }
        }

    override suspend fun setLocal(model: LocalSharedPreferencesModel): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                local = model
            }
        }

    override suspend fun listIdNature(): Result<List<Int>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                idNatureList
            }
        }

    override suspend fun setIdNatureList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                idNatureList = idList
            }
        }

    override suspend fun getRegAttendant(): Result<Long?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                regAttendant
            }
        }

    override suspend fun getIdCar(): Result<Int?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                idCar
            }
        }

    override suspend fun listIdTypeAccident(): Result<List<Int>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                idTypeAccidentList
            }
        }

    override suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                idTypeAccidentList = idList
            }
        }

    override suspend fun getLocal(): Result<LocalSharedPreferencesModel> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                local
            }
        }

    override suspend fun listIdDataLocal(): Result<List<Int>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                idDataLocalList
            }
        }

    override suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                idDataLocalList = idList
            }
        }

    override suspend fun listIdSupportTeams(): Result<List<Int>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                idSupportTeamsList
            }
        }

    override suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                idSupportTeamsList = idList
            }
        }
}