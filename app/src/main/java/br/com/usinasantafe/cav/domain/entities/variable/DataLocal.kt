package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal

data class DataLocal(
    val id: Int,
    val option: OptionDataLocal,
    val item: ItemDataLocal
)