package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.lib.Type
import br.com.usinasantafe.cav.lib.TypeDetail

data class Config(
    var number: Long? = null,
    var password: String? = null,
    var idServ: Int? = null,
    var version: String? = null,
    var statusSend: StatusSend = StatusSend.STARTED,
    var flagUpdate: Boolean = false,
)
