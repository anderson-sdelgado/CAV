package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.lib.FlagUpdate
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.required

data class ConfigSharedPreferencesModel(
    var number: Long? = null,
    var password: String? = null,
    var idServ: Int? = null,
    var version: String? = null,
    var statusSend: StatusSend = StatusSend.STARTED,
    val flagUpdate: FlagUpdate = FlagUpdate.OUTDATED,
)

fun ConfigSharedPreferencesModel.sharedPreferencesModelToEntity(): Config {
    return with(this) {
        Config(
            number = number,
            password = password,
            idServ = idServ,
            version = version,
            statusSend = statusSend,
            flagUpdate = flagUpdate
        )
    }
}

fun Config.entityToSharedPreferencesModel(): ConfigSharedPreferencesModel {
    return with(this) {
        ConfigSharedPreferencesModel(
            number = ::number.required(),
            password = ::password.required(),
            idServ = ::idServ.required(),
            version = ::version.required(),
            statusSend = statusSend,
            flagUpdate = flagUpdate
        )
    }
}