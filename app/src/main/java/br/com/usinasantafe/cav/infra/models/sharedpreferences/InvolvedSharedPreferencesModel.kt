package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.Involved

data class InvolvedSharedPreferencesModel(
    var id: Int? = null,
    var document: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var state: Int? = null,
    var detail: Int? = null,
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
