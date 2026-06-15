package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.lib.State

data class ColabCard(
    var id: Int? = null,
    var reg: Long? = null,
    var state: State? = null,
    var detail: String? = null
)