package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.BasicCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.DeleteCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.InsertCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.domain.repositories.variable.UpdateCardRepository
import javax.inject.Inject

class ICardRepository @Inject constructor(
    private val basicRepository: IBasicCardRepository,
    private val insertRepository: IInsertCardRepository,
    private val recoverDataRepository: IRecoverDataCardRepository,
    private val updateRepository: IUpdateCardRepository,
    private val deleteRepository: IDeleteCardRepository
): CardRepository,
    BasicCardRepository by basicRepository,
    InsertCardRepository by insertRepository,
    RecoverDataCardRepository by recoverDataRepository,
    UpdateCardRepository by updateRepository,
    DeleteCardRepository by deleteRepository

