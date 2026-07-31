package br.com.usinasantafe.cav.domain.entities.variable

import br.com.usinasantafe.cav.lib.State

data class PeopleExternal(
    var id: Int? = null,
    var document: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var state: State? = null,
    var detail: String? = null,
)