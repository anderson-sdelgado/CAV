package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetDetailTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetDetail(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setDetailEquip - Option INSERT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.setDetailEquip("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote EQUIP`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setDetailEquip("Test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailEquip - Option INSERT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.setDetailEquip("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote EQUIP_SEC`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setDetailEquip("Test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailColab - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.setDetailColab("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.setDetailColab("Test")
            ).thenReturn(
                Result.success(10)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                10
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailColab - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.setDetailColab("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.setDetailColab("Test")
            ).thenReturn(
                Result.success(35)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                35
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailVehicle - Option INSERT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.setDetailVehicle("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.VEHICLE,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailVehicle"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote VEHICLE`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.VEHICLE,
                idMain = 0,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).setDetailVehicle("Test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailInvolved - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                Result.success(60)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                60
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailInvolved - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                Result.success(70)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                70
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailInvolved - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                Result.success(30)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                30
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository setDetailInvolved - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.setDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.setDetailInvolved("Test")
            ).thenReturn(
                Result.success(80)
            )
            val result = usecase(
                text = "Test",
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                80
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailEquip - Option EDIT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.updateDetailEquip("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
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
                "ISetDetail -> ICardRepository.updateDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote EQUIP`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailEquip("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailEquipSecondary - Option EDIT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.updateDetailEquipSecondary("Test", 1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
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
                "ISetDetail -> ICardRepository.updateDetailEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote EQUIP_SEC`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            verify(cardRepository, atLeastOnce()).updateDetailEquipSecondary("Test", 1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailColab - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.updateDetailColab("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote COLAB`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailColab("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailPassengerColab - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.updateDetailPassengerColab("Test", 1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            verify(cardRepository, atLeastOnce()).updateDetailPassengerColab("Test", 1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailVehicle - Option EDIT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.updateDetailVehicle("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.VEHICLE,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailVehicle"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote VEHICLE`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.VEHICLE,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailVehicle("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailDriver - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.updateDetailDriver("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote DRIVER`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailDriver("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailPassengerInvolved - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.updateDetailPassengerInvolved("Test", 1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            verify(cardRepository, atLeastOnce()).updateDetailPassengerInvolved("Test", 1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailInvolved - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.updateDetailInvolved("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailInvolved("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    ////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository updateDetailWitness - Option EDIT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.updateDetailWitness("Test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.updateDetailWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDetail -> ICardRepository.updateDetailWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote WITNESS`() =
        runTest {
            val result = usecase(
                text = "Test",
                option = Option.EDIT,
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            verify(cardRepository, atLeastOnce()).updateDetailWitness("Test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}