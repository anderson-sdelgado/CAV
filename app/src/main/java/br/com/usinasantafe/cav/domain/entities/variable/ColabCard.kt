package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.lib.State

data class ColabCard(
    var id: Int? = null,
    var reg: Long? = null,
    var state: State? = null,
    val flagRealizedBreathalyzer: Boolean? = null,
    val flagResultBreathalyzer: Boolean? = null,
    val countBreathalyzer: Double? = null,
    var detail: String? = null
)