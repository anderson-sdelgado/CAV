package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.domain.entities.variable.Card
import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.utils.required
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val formatter = SimpleDateFormat(
    "dd/MM/yyyy HH:mm",
    Locale.forLanguageTag("pt-BR")
)

data class CardRetrofitModelOutput(
    val id: Int,
    val regAttendant: Long,
    val idCar: Int,
    val address: String?,
    val latitude: Double?,
    val longitude: Double?,
    val idNatureList: List<Int>,
    val idTypeAccidentList: List<Int>,
    val idDataLocalList: List<Int>,
    val idSupportTeamsList: List<Int>,
    val vehicleInvolvedList: List<VehicleInvolvedRetrofitModel>,
    val vehicleOwnList: List<VehicleOwnRetrofitModel>,
    val involvedList: List<InvolvedRetrofitModel>,
    val witnessList: List<WitnessRetrofitModel>,
    val urlPhotoList: List<String>,
    val obs: String?,
    val dateHour: String
)

data class CardRetrofitModelInput(
    val idServ: Int
)

fun CardRoomModel.roomModelToRetrofitModel(
    vehicleInvolvedList: List<VehicleInvolvedRetrofitModel>,
    vehicleOwnList: List<VehicleOwnRetrofitModel>,
    involvedList: List<InvolvedRetrofitModel>,
    witnessList: List<WitnessRetrofitModel>
): CardRetrofitModelOutput {
    return with(this) {
        CardRetrofitModelOutput(
            id = id!!,
            regAttendant = regAttendant,
            idCar = idCar,
            address = address,
            latitude = latitude,
            longitude = longitude,
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            vehicleInvolvedList = vehicleInvolvedList,
            vehicleOwnList = vehicleOwnList,
            involvedList = involvedList,
            witnessList = witnessList,
            urlPhotoList = urlPhotoList,
            obs = obs,
            dateHour = formatter.format(dateHour)
        )
    }
}


fun CardRetrofitModelInput.retrofitToEntity(): Card {
    return Card(
        idServ = ::idServ.required(),
    )
}