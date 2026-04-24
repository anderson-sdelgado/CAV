package br.com.usinasantafe.cav.infra.models.retrofit.variable

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.utils.required

data class ConfigRetrofitModelOutput(
    var number: Long,
    var version: String,
)

data class ConfigRetrofitModelInput(
    val idServ: Int,
)

fun Config.entityToRetrofitModel(): ConfigRetrofitModelOutput {
    return ConfigRetrofitModelOutput(
        number = ::number.required(),
        version = ::version.required(),
    )
}

fun ConfigRetrofitModelInput.retrofitToEntity(): Config {
    return Config(
        idServ = ::idServ.required(),
    )
}