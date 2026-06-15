package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Colab
import br.com.usinasantafe.cav.lib.State

data class ColabSharedPreferencesModel(
    var id: Int? = null,
    var reg: Long? = null,
    var state: State? = null,
    var detail: String? = null
)

fun ColabSharedPreferencesModel.sharedPreferencesModelToEntity(): Colab {
    return with(this) {
        Colab(
            id = id,
            reg = reg,
            state = state,
            detail = detail
        )
    }
}

fun Colab.entityToSharedPreferencesModel(): ColabSharedPreferencesModel {
    return with(this) {
        ColabSharedPreferencesModel(
            id = id,
            reg = reg,
            state = state,
            detail = detail
        )
    }
}