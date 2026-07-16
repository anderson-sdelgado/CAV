package br.com.usinasantafe.cav.infra.models.retrofit.variable

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
    val urlPhotoList: List<String>,
    val obs: String?
)

data class CardRetrofitModelOutputInput(
    val idServ: Int
)