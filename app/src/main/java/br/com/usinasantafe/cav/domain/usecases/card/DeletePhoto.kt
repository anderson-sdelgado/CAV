package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface DeletePhoto {
    suspend operator fun invoke(url: String): EmptyResult
}

class IDeletePhoto @Inject constructor(
    private val cardRepository: CardRepository
): DeletePhoto {

    override suspend fun invoke(url: String): EmptyResult =
        call(getClassAndMethod()) {
            cardRepository.deletePhoto(url).getOrThrow()
        }

}
