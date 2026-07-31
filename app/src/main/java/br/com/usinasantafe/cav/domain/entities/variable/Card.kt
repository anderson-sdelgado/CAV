package br.com.usinasantafe.cav.domain.entities.variable

data class Card(
    var regAttendant: Long? = null,
    var idCar: Int? = null,
    var local: Local? = null,
    var idNatureList: List<Int> = emptyList(),
    var idTypeAccidentList: List<Int> = emptyList(),
    var idDataLocalList: List<Int> = emptyList(),
    var idSupportTeamsList: List<Int> = emptyList(),
    var vehicleOwnList: List<VehicleOwn> = emptyList(),
    var vehicleExternalList: List<VehicleExternal> = emptyList(),
    var involvedExternalList: List<PeopleExternal> = emptyList(),
    var witnessExternalList: List<PeopleExternal> = emptyList(),
    var involvedColabList: List<ColabCard> = emptyList(),
    var witnessColabList: List<ColabCard> = emptyList(),
    var photoList: List<String> = emptyList(),
    var obs: String? = null,
    var idServ: Int? = null,
)