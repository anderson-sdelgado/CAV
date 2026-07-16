package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.SaveAndSendCardRepository
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.EquipSecRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleOwnRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToPassengerInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToWitnessRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class ISaveAndSendCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val involvedRoomDatasource: InvolvedRoomDatasource,
    private val vehicleInvolvedRoomDatasource: VehicleInvolvedRoomDatasource,
    private val passengerColabRoomDatasource: PassengerColabRoomDatasource,
    private val passengerInvolvedRoomDatasource: PassengerInvolvedRoomDatasource,
    private val equipSecRoomDatasource: EquipSecRoomDatasource,
    private val vehicleOwnRoomDatasource: VehicleOwnRoomDatasource,
    private val witnessRoomDatasource: WitnessRoomDatasource,
    private val cardRoomDatasource: CardRoomDatasource
): SaveAndSendCardRepository {

    override suspend fun save(): EmptyResult =
        call(getClassAndMethod()) {
            val cardSharedPreferencesModel = cardSharedPreferencesDatasource.get().getOrThrow()
            val model = cardSharedPreferencesModel.sharedPreferencesModelToRoomModel()
            val idCard = cardRoomDatasource.add(model).getOrThrow()

            cardSharedPreferencesModel.vehicleOwnList.forEach { vehicleOwn ->
                val model = vehicleOwn.sharedPreferencesModelToInvolvedRoomModel(idCard)
                val idVehicle = vehicleOwnRoomDatasource.add(model).getOrThrow()
                vehicleOwn.passengerColabList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedRoomModel(idVehicle)
                    passengerColabRoomDatasource.add(model).getOrThrow()
                }
                vehicleOwn.equipSecList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedRoomModel(idVehicle)
                    equipSecRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.vehicleInvolvedList.forEach { vehicleInvolved ->
                val model = vehicleInvolved.sharedPreferencesModelToInvolvedRoomModel(idCard)
                val idVehicle = vehicleInvolvedRoomDatasource.add(model).getOrThrow()
                vehicleInvolved.passengerInvolvedList.forEach {
                    val model = it.sharedPreferencesModelToPassengerInvolvedRoomModel(idVehicle)
                    passengerInvolvedRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.involvedList.forEach { involved ->
                val model = involved.sharedPreferencesModelToInvolvedRoomModel(idCard)
                involvedRoomDatasource.add(model).getOrThrow()
            }

            cardSharedPreferencesModel.witnessList.forEach { witness ->
                val model = witness.sharedPreferencesModelToWitnessRoomModel(idCard)
                witnessRoomDatasource.add(model).getOrThrow()
            }

        }

    override suspend fun send(): EmptyResult {
        TODO("Not yet implemented")
    }

}