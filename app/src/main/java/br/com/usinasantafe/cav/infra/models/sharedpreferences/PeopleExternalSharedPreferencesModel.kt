package br.com.usinasantafe.cav.infra.models.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.lib.State

data class PeopleExternalSharedPreferencesModel(
    var id: Int? = null,
    var document: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var state: State? = null,
    var detail: String? = null,
)

fun PeopleExternalSharedPreferencesModel.sharedPreferencesModelToEntity(): PeopleExternal {
    return with(this) {
        PeopleExternal(
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

fun PeopleExternal.entityToSharedPreferencesModel(): PeopleExternalSharedPreferencesModel{
    return with(this) {
        PeopleExternalSharedPreferencesModel(
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
