package br.com.usinasantafe.cav.domain.entities.variable

data class Card(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: Local = Local(),
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList()
)

data class Local(
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)

