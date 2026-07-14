package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.tryCatch
import javax.inject.Inject

interface CancelCard {
    suspend operator fun invoke(): Result<Unit>
}

class ICancelCard @Inject constructor(
    private val cardRepository: CardRepository
): CancelCard {

    override suspend fun invoke(): Result<Unit> =
        call(getClassAndMethod()) {
            val list = cardRepository.listPhoto().getOrThrow()
            list.forEach { path ->
                tryCatch("deletePhoto") {
                    val file = java.io.File(path)
                    if (file.exists()) {
                        file.delete()
                    }
                }
            }
            cardRepository.clean().getOrThrow()
        }

}
