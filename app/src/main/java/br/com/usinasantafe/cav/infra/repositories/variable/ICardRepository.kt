package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.BasicCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.DeleteCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.InsertCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.UpdateCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.repositories.variable.card.IBasicCardRepository
import br.com.usinasantafe.cav.infra.repositories.variable.card.IDeleteCardRepository
import br.com.usinasantafe.cav.infra.repositories.variable.card.IInsertCardRepository
import br.com.usinasantafe.cav.infra.repositories.variable.card.IRecoverDataCardRepository
import br.com.usinasantafe.cav.infra.repositories.variable.card.IUpdateCardRepository
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class ICardRepository @Inject constructor(
    private val basicRepository: IBasicCardRepository,
    private val insertRepository: IInsertCardRepository,
    private val recoverDataRepository: IRecoverDataCardRepository,
    private val updateRepository: IUpdateCardRepository,
    private val deleteRepository: IDeleteCardRepository,
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
): CardRepository,
    BasicCardRepository by basicRepository,
    InsertCardRepository by insertRepository,
    RecoverDataCardRepository by recoverDataRepository,
    UpdateCardRepository by updateRepository,
    DeleteCardRepository by deleteRepository {

    override suspend fun clean(): EmptyResult =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.clean().getOrThrow()
        }

    override suspend fun has(): Result<Boolean> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.has().getOrThrow()
        }

}