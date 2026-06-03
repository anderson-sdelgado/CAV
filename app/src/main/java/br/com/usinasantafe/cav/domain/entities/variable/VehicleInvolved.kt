package br.com.usinasantafe.cav.domain.entities.variable

data class VehicleInvolved(
    var id: Int? = null,
    var vehicle: Vehicle,
    var driver: Involved,
    var passengerInvolvedList: List<Involved>
)
