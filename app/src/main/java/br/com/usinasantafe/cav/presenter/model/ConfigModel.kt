package br.com.usinasantafe.cav.presenter.model

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.utils.required


data class ConfigModel(
    val number: String,
    val password: String,
)

fun Config.toConfigModel(): ConfigModel {
    return with(this){
        ConfigModel(
            number = this::number.required().toString(),
            password = this::password.required(),
        )
    }
}
