package br.com.usinasantafe.cav.infra.models.retrofit.stable

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams

data class SupportTeamsRetrofitModel(
    val id: Int,
    val desc: String
)

fun SupportTeamsRetrofitModel.retrofitModelToEntity(): SupportTeams {
    return with(this) {
        SupportTeams(
            id = id,
            desc = desc
        )
    }
}
