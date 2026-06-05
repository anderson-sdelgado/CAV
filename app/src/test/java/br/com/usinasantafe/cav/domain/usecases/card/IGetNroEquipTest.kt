package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetNroEquipTest {

    private val cardRepository = mock<CardRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IGetNroEquip(
        cardRepository = cardRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getIdEquip`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetNroEquip -> ICardRepository.getIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getNroById and flowNote is EQUIP`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                Result.success(10)
            )
            whenever(
                equipRepository.getNroById(10)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getNroById",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetNroEquip -> IEquipRepository.getNroById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }
    
    @Test
    fun `Check return correct if function execute successfully and flowNote is EQUIP`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                Result.success(10)
            )
            whenever(
                equipRepository.getNroById(10)
            ).thenReturn(
                Result.success(2200L)
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "2200"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdEquipSecondary`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetNroEquip -> ICardRepository.getIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getNroById and flowNote is EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                Result.success(20)
            )
            whenever(
                equipRepository.getNroById(20)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getNroById",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetNroEquip -> IEquipRepository.getNroById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and flowNote is EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                Result.success(20)
            )
            whenever(
                equipRepository.getNroById(20)
            ).thenReturn(
                Result.success(330L)
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "330"
            )
        }
}