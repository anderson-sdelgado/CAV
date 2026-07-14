package br.com.usinasantafe.cav.domain.usecases.card

import android.net.Uri
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetPhoto {
    suspend operator fun invoke(url: String): EmptyResult
}

class ISetPhoto @Inject constructor(
    private val cardRepository: CardRepository
): SetPhoto {

    override suspend fun invoke(url: String): EmptyResult =
        call(getClassAndMethod()) {
            cardRepository.setPhoto(url).getOrThrow()
        }

}