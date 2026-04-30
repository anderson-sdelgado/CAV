package br.com.usinasantafe.cav.domain.entities.variable

data class Card(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    val local: Local? = null
)

data class Local(
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)
