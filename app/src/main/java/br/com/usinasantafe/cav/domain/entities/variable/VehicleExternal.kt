package br.com.usinasantafe.cav.domain.entities.variable

data class VehicleExternal(
    var id: Int? = null,
    var vehicle: Vehicle,
    var driver: PeopleExternal,
    var passengerPeopleExternalList: List<PeopleExternal>
)
