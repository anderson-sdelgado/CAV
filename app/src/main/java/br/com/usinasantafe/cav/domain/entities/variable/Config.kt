package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.lib.FlagUpdate
import br.com.usinasantafe.cav.lib.StatusSend

data class Config(
    var number: Long? = null,
    var password: String? = null,
    var idServ: Int? = null,
    var version: String? = null,
    var statusSend: StatusSend = StatusSend.STARTED,
    val flagUpdate: FlagUpdate = FlagUpdate.OUTDATED,
)
