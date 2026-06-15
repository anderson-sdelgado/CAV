package br.com.usinasantafe.cav.domain.entities.variable

data class VehicleOwn(
    var id: Int? = null,
    var equipCard: EquipCard = EquipCard(),
    var equipCardSecList: List<EquipCard> = emptyList(),
    var colabCard: ColabCard = ColabCard(),
    var passengerColabCardList: List<ColabCard> = emptyList()
)