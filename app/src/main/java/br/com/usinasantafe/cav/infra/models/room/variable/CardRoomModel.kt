package br.com.usinasantafe.cav.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.lib.TB_CARD
import br.com.usinasantafe.cav.utils.required

@Entity(tableName = TB_CARD)
data class CardRoomModel(
    @PrimaryKey
    val id: Int? = null,
    val regAttendant: Long,
    val idCar: Int,
    val address: String?,
    val latitude: Double?,
    val longitude: Double?,
    val idNatureList: List<Int>,
    val idTypeAccidentList: List<Int>,
    val idDataLocalList: List<Int>,
    val idSupportTeamsList: List<Int>,
    val urlPhotoList: List<String>,
    val obs: String?,
    var statusSend: StatusSend = StatusSend.SEND,
    var idServ: Int? = null,
)

fun CardSharedPreferencesModel.sharedPreferencesModelToRoomModel(): CardRoomModel {
    return with(this) {
        CardRoomModel(
            regAttendant = ::regAttendant.required(),
            idCar = ::idCar.required(),
            address = ::local.required().address,
            latitude = ::local.required().latitude,
            longitude = ::local.required().longitude,
            idNatureList = idNatureList,
            idTypeAccidentList = idTypeAccidentList,
            idDataLocalList = idDataLocalList,
            idSupportTeamsList = idSupportTeamsList,
            urlPhotoList = urlPhotoList,
            obs = obs
        )
    }
}

