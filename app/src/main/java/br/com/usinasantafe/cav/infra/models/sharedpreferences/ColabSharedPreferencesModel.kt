package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.lib.State

data class ColabSharedPreferencesModel(
    var id: Int? = null,
    var reg: Long? = null,
    var state: State? = null,
    var detail: String? = null
)

fun ColabSharedPreferencesModel.sharedPreferencesModelToEntity(): ColabCard {
    return with(this) {
        ColabCard(
            id = id,
            reg = reg,
            state = state,
            detail = detail
        )
    }
}

fun ColabCard.entityToSharedPreferencesModel(): ColabSharedPreferencesModel {
    return with(this) {
        ColabSharedPreferencesModel(
            id = id,
            reg = reg,
            state = state,
            detail = detail
        )
    }
}