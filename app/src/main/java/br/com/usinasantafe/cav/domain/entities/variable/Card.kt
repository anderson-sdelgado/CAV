package br.com.usinasantafe.cav.domain.entities.variable

data class Card(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: Local = Local(),
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList(),
    var vehicleOwnList: List<VehicleOwn> = emptyList(),
    var vehicleInvolvedList: List<VehicleInvolved> = emptyList(),
    var involvedList: List<Involved> = emptyList(),
    var witnessList: List<Involved> = emptyList(),
    var obs: String? = null,
)