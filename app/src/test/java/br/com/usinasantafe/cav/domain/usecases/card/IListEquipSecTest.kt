package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListEquipSecTest {

    private val cardRepository = mock<CardRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IListEquipSec(
        cardRepository = cardRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdEquipSecondary`() =
        runTest {
            whenever(
                cardRepository.listEquipSecondary(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListEquipSec -> ICardRepository.listIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository listByIdList`() =
        runTest {
            whenever(
                cardRepository.listEquipSecondary(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        EquipCard(
                            idEquip = 10
                        ),
                        EquipCard(
                            idEquip = 20
                        )
                    )
                )
            )
            whenever(
                equipRepository.listByIdList(listOf(10, 20))
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.listByIdList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListEquipSec -> IEquipRepository.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty list if function execute successfully with empty list`() =
        runTest {
            whenever(
                cardRepository.listEquipSecondary(1)
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                equipRepository.listByIdList(emptyList())
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!.isEmpty(),
                true
            )
        }

    @Test
    fun `Check return list ItemListScreenModel if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.listEquipSecondary(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        EquipCard(
                            idEquip = 10
                        ),
                        EquipCard(
                            idEquip = 20
                        )
                    )
                )
            )
            whenever(
                equipRepository.listByIdList(listOf(10, 20))
            ).thenReturn(
                Result.success(
                    listOf(
                        Equip(
                            id = 10,
                            nro = 100,
                            description = "EQUIP 1"
                        ),
                        Equip(
                            id = 20,
                            nro = 200,
                            description = "EQUIP 2"
                        )
                    )
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list.size,
                2
            )
            assertEquals(
                list[0],
                ItemListScreenModel(
                    id = 10,
                    desc = "EQUIP 1"
                )
            )
            assertEquals(
                list[1],
                ItemListScreenModel(
                    id = 20,
                    desc = "EQUIP 2"
                )
            )
        }

}
