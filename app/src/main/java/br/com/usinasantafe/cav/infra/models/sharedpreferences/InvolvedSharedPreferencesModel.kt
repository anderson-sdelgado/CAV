package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.lib.State

data class InvolvedSharedPreferencesModel(
    var id: Int? = null,
    var document: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var state: State? = null,
    var detail: String? = null,
)

fun InvolvedSharedPreferencesModel.sharedPreferencesModelToEntity(): Involved {
    return with(this) {
        Involved(
            id = id,
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state,
            detail = detail
        )
    }
}

fun Involved.entityToSharedPreferencesModel(): InvolvedSharedPreferencesModel{
    return with(this) {
        InvolvedSharedPreferencesModel(
            id = id,
            document = document,
            name = name,
            phone = phone,
            address = address,
            state = state,
            detail = detail
        )
    }
}
