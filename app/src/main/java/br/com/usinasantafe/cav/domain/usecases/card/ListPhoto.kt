package br.com.usinasantafe.cav.domain.usecases.card

import android.net.Uri
import androidx.core.net.toUri
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListPhoto {
    suspend operator fun invoke(): Result<List<String>>
}

class IListPhoto @Inject constructor(
    private val cardRepository: CardRepository
): ListPhoto {

    override suspend fun invoke(): Result<List<String>> =
        call(getClassAndMethod()) {
            cardRepository.listPhoto().getOrThrow()
        }

}