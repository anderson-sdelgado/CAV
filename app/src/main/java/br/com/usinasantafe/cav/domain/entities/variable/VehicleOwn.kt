package br.com.usinasantafe.cav.domain.entities.variable

data class VehicleOwn(
    var id: Int? = null,
    var equip: Equip = Equip(),
    var equipSecList: List<Equip> = emptyList(),
    var colab: Colab = Colab(),
    var passengerColabList: List<Colab> = emptyList()
)