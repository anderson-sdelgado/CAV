package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetLocal {
    suspend operator fun invoke(
        address: String,
        latitude: Double? = null,
        longitude: Double? = null
    ): EmptyResult
}

class ISetLocal @Inject constructor(
    private val cardRepository: CardRepository,
): SetLocal {

    override suspend fun invoke(
        address: String,
        latitude: Double?,
        longitude: Double?
    ): EmptyResult =
        call(getClassAndMethod()) {
            val entity = cardRepository.getLocal().getOrThrow() ?: Local()
            entity.address = address
            entity.latitude = latitude ?: entity.latitude
            entity.longitude = longitude ?: entity.longitude
            cardRepository.setLocal(entity).getOrThrow()
        }

}
